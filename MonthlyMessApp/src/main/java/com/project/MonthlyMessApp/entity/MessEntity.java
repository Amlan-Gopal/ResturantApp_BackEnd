package com.project.MonthlyMessApp.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
public class MessEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int messId;
	@NotNull(message = "Start Date cant be empty in mess entity")
	@Temporal(TemporalType.DATE)
	private Date startDate;
	@NotNull(message = "Paid Date cant be empty in mess entity")
	@Temporal(TemporalType.DATE)
	private Date paidDate;
	@NotNull(message = "Is paid cant be empty in mess entity")
	private boolean isPaid;
	@OneToOne(cascade = CascadeType.ALL)
	@NotNull(message = "Customer details should be provided in mess entity")
	private Customer customer;
	
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public int getMessId() {
		return messId;
	}
	public void setMessId(int messId) {
		this.messId = messId;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startingDate) {
		this.startDate = startingDate;
	}
	public Date getPaidDate() {
		return paidDate;
	}
	public void setPaidDate(Date paidDate) {
		this.paidDate = paidDate;
	}
	public boolean isPaid() {
		return isPaid;
	}
	public void setPaid(boolean isPaid) {
		this.isPaid = isPaid;
	}
	
	
}
