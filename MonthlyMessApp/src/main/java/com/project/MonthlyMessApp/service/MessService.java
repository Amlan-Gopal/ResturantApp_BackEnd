package com.project.MonthlyMessApp.service;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.MonthlyMessApp.entity.Address;
import com.project.MonthlyMessApp.entity.Customer;
import com.project.MonthlyMessApp.model.MessDairy;
import com.project.MonthlyMessApp.model.CustomerAddress;
import com.project.MonthlyMessApp.model.CustomerDetails;
import com.project.MonthlyMessApp.entity.MessEntity;
import com.project.MonthlyMessApp.exceptions.EntityNotSavedException;
import com.project.MonthlyMessApp.exceptions.NoResourceFoundException;
import com.project.MonthlyMessApp.repository.MessRepository;

@Service
public class MessService {
	
	private final double MONTHLY_MESS_AMOUNT = 3000.00;

	@Autowired
	MessRepository messRepo;
	
	public MessDairy addCustomer(MessDairy newDairy) throws Exception {
			
		MessEntity messEntity = convertToMessEntity(newDairy, true);
	    MessEntity savedMessEntity = messRepo.save(messEntity);
      
	    if(savedMessEntity == null) {
	    	throw new EntityNotSavedException("Mess Info could not be saved");
	    }
	   
	    MessDairy savedMessDairy = convertToMessModel(savedMessEntity);
	    
		return savedMessDairy;	
	}

	public Set<MessDairy> getAllMembers() throws Exception{
		
		Set<MessDairy> allMessDairies = new LinkedHashSet<>();
		
		List<MessEntity> allMessEntities = messRepo.findAll();
		if(allMessEntities == null || allMessEntities.isEmpty()) {
			throw new NoResourceFoundException("No customer has been registered");
		}

		for(MessEntity messEntity: allMessEntities) {
			
			MessDairy messDairy = convertToMessModel(messEntity);
			messDairy = paymentCheckAndUpdate(messDairy);
			allMessDairies.add(messDairy);
		}
		
		return allMessDairies;
	}
	
	public MessDairy clearDue(int messId, double amount) throws Exception {
		
		int pendingMonths = 0;
		int monthsToAdd = 0;
		MessEntity messEntity = new MessEntity();
		Optional<MessEntity> messEntityOptional = messRepo.findById(messId);
		if(messEntityOptional.isPresent()) {
			messEntity = messEntityOptional.get();
		}else {
			throw new NoResourceFoundException("Searched Element Not Found");
		}
		if(messEntity == null) {
			throw new NoResourceFoundException("Searched Element Not Found");
		}
		MessDairy messDairy = convertToMessModel(messEntity);
		messDairy = paymentCheckAndUpdate(messDairy);
		pendingMonths = calculatePendingMonths(messDairy.getPaidDate());
		
		if(pendingMonths > 0) {
			monthsToAdd = (int)(amount/ MONTHLY_MESS_AMOUNT);
			Date updatedPaidDate = updatePaidDate(messDairy.getPaidDate(), monthsToAdd);
			messDairy.setPaid(true);
			messDairy.setPaidDate(updatedPaidDate);
			
			MessEntity updatedMessEntity = convertToMessEntity(messDairy, false);
			MessEntity savedEntity = messRepo.save(updatedMessEntity);
			
			 if(savedEntity == null) {
			    	throw new EntityNotSavedException("Mess Info could not be saved");
			    }
			 
		}
		pendingMonths = calculatePendingMonths(messDairy.getPaidDate());
		messDairy.setPendingMonths(pendingMonths);
		
		return messDairy;
	}
	
	public MessDairy getCustomerDetailsByName(String customerName) throws Exception {
	
		MessEntity messEntity = messRepo.findMessDairyByCustomerName(customerName);
		
		if(messEntity == null) {
			throw new NoResourceFoundException("Searched Element Not Found");
		}
		
		MessDairy messDairy = convertToMessModel(messEntity);
		messDairy = paymentCheckAndUpdate(messDairy);

		return messDairy;
	}
	
	private MessEntity convertToMessEntity(MessDairy newDiary, boolean isNewEntity) {
		
		MessEntity messEntity = new MessEntity();
		
		CustomerDetails inputCustomer = newDiary.getCustomer();
		CustomerAddress customerInputAddress = inputCustomer.getAddress();
		
		Address address = new Address();
		address.setHouseNo(customerInputAddress.getHouseNo());
		address.setLocality(customerInputAddress.getLocality());
		address.setCity(customerInputAddress.getCity());
		
		Customer customer = new Customer();
		customer.setCustomerName(inputCustomer.getCustomerName());
		customer.setPhoneNumber(inputCustomer.getPhoneNumber());
		customer.setCustomerAddress(address);
		if(!isNewEntity) {
			customer.setCustomerId(inputCustomer.getCustomerId());
		}
		
	    messEntity.setCustomer(customer);
		
	    if(isNewEntity) {
			messEntity.setPaid(true);
			messEntity.setStartDate(new Date());
			messEntity.setPaidDate(new Date());
		}else {
			messEntity.setPaid(newDiary.isPaid());
			messEntity.setStartDate(newDiary.getStartingDate());
			messEntity.setPaidDate(newDiary.getPaidDate());
			messEntity.setMessId(newDiary.getMessId());
		}

		
		return messEntity;
	}
	
	private MessDairy convertToMessModel(MessEntity inputEntity) {
		
		MessDairy messDairy = new MessDairy();
		CustomerDetails customerDetails = new CustomerDetails();
		CustomerAddress customerAddress = new CustomerAddress();
		
		Customer customer = inputEntity.getCustomer();
		Address address = customer.getCustomerAddress();
		
		customerAddress.setHouseNo(address.getHouseNo());
		customerAddress.setLocality(address.getLocality());
		customerAddress.setCity(address.getCity());
		
		customerDetails.setCustomerId(customer.getCustomerId());
		customerDetails.setCustomerName(customer.getCustomerName());
		customerDetails.setPhoneNumber(customer.getPhoneNumber());
		customerDetails.setAddress(customerAddress);
		
		messDairy.setMessId(inputEntity.getMessId());
		messDairy.setCustomer(customerDetails);
		messDairy.setPaid(inputEntity.isPaid());
		messDairy.setStartingDate(inputEntity.getStartDate());
		messDairy.setPaidDate(inputEntity.getPaidDate());
		messDairy.setCurrentDate(new Date());
	    
		return messDairy;
	}
	
	private MessDairy paymentCheckAndUpdate(MessDairy inputMessDairy) throws Exception {
		
		int pendingMonths = calculatePendingMonths(inputMessDairy.getPaidDate());
		if(pendingMonths > 0) {
			inputMessDairy.setPendingMonths(pendingMonths);
			inputMessDairy.setPaid(false);
			
			MessEntity messEntity = convertToMessEntity(inputMessDairy, false);
			MessEntity savedEntity = messRepo.save(messEntity);
			
			if(savedEntity == null) {
				throw new EntityNotSavedException("The entity could not been saved");
			}
		}
		
		return inputMessDairy;
	}
	
	private int calculatePendingMonths(Date paidDate) {
		int pendingMonths = 0;
		Calendar calander1 = Calendar.getInstance();
        Calendar calander2 = Calendar.getInstance();
		calander1.setTime(new Date());
        calander2.setTime(paidDate);
         
        pendingMonths = calander1.get(Calendar.MONTH) 
                         - calander2.get(Calendar.MONTH);

		return pendingMonths;
	}
	
	private Date updatePaidDate(Date inputPaidDate, int months) {
		
		Calendar calendar1 = Calendar.getInstance();
		calendar1.setTime(inputPaidDate);
		calendar1.add(Calendar.MONTH, months);
		Date updatedPaidDate = calendar1.getTime();
		return updatedPaidDate;
	}
}
