package com.project.MonthlyMessApp.controller;

import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.MonthlyMessApp.model.MessDairy;
import com.project.MonthlyMessApp.service.MessService;

@RestController
@RequestMapping("/mess")
@Validated
public class MessController {

	@Autowired
	MessService messService;
	
	@GetMapping("/all")
	public Set<MessDairy> getAllMembers() throws Exception {
		
		Set<MessDairy> messDairies = messService.getAllMembers();
		return messDairies;
	}
	
	@PostMapping("/add")
	public ResponseEntity<MessDairy> addCustomer(@Valid @RequestBody MessDairy newDairy) throws Exception {
		
		MessDairy messDairy = messService.addCustomer(newDairy);
		return new ResponseEntity<MessDairy>(messDairy, HttpStatus.CREATED);
	}
	
	@PutMapping("/pay/{messId}/{amount}")
	public ResponseEntity<MessDairy> clearDue(@PathVariable("messId") int messId, @PathVariable("amount") double amount) throws Exception{
		
		MessDairy updatedMessDairy = messService.clearDue(messId, amount);
		return new ResponseEntity<MessDairy>(updatedMessDairy, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/{name}")
	public MessDairy getMemberByName(@PathVariable("name") String customerName) throws Exception {
		
		MessDairy messDairy = messService.getCustomerDetailsByName(customerName);
		return messDairy;
	}
}
