package com.project.MonthlyMessApp.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class CustomerDetails {

	private int customerId;
	@NotBlank(message = "Customer should contain some name")
	private String customerName;
	@NotBlank(message = "Customer should fill the phone number")
	//Add phone check
	private String phoneNumber;
	@NotNull(message = "Customer should have an address")
	private CustomerAddress address;
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public CustomerAddress getAddress() {
		return address;
	}
	public void setAddress(CustomerAddress address) {
		this.address = address;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	
}
