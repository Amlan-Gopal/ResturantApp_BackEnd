package com.project.MonthlyMessApp.model;

import java.util.Date;

import javax.validation.constraints.NotNull;

public class MessDairy {

	private int messId;
	@NotNull(message = "Each mess person should have customer details")
	private CustomerDetails customer;
	private Date startingDate;
	private Date paidDate;
	private Date currentDate;
	private boolean isPaid;
	private int pendingMonths;
	public int getMessId() {
		return messId;
	}
	public void setMessId(int messId) {
		this.messId = messId;
	}
	public CustomerDetails getCustomer() {
		return customer;
	}
	public void setCustomer(CustomerDetails customer) {
		this.customer = customer;
	}
	public Date getStartingDate() {
		return startingDate;
	}
	public void setStartingDate(Date startingDate) {
		this.startingDate = startingDate;
	}
	public boolean isPaid() {
		return isPaid;
	}
	public void setPaid(boolean isPaid) {
		this.isPaid = isPaid;
	}
	public int getPendingMonths() {
		return pendingMonths;
	}
	public void setPendingMonths(int pendingMonths) {
		this.pendingMonths = pendingMonths;
	}
	public Date getPaidDate() {
		return paidDate;
	}
	public void setPaidDate(Date paidDate) {
		this.paidDate = paidDate;
	}
	public Date getCurrentDate() {
		return currentDate;
	}
	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
	}
	
	
}
