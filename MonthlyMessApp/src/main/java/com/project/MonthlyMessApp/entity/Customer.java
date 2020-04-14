package com.project.MonthlyMessApp.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int customerId;
	@NotBlank(message = "Customer name cant be blank in mess entity")
	private String customerName;
	@NotBlank(message = "Customer phone number cant be blank in mess entity")
	//Add phone check
	private String phoneNumber;
	@NotNull(message = "Customer address should be provided in mess entity")
	private Address customerAddress;
	@OneToOne(mappedBy = "customer")
	private MessEntity messDairy;
	
	public MessEntity getMessDairy() {
		return messDairy;
	}
	public void setMessDairy(MessEntity messDairy) {
		this.messDairy = messDairy;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public Address getCustomerAddress() {
		return customerAddress;
	}
	public void setCustomerAddress(Address customerAddress) {
		this.customerAddress = customerAddress;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	
}
