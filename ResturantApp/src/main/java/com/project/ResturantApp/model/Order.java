package com.project.ResturantApp.model;

import java.util.Date;
import java.util.Set;
import java.util.SortedSet;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Order {

	private int orderId;
	@NotBlank(message = "Customer namecant be empty in an order")
	private String customerName;
	@Digits(message = "Total price should be added in the order", fraction = 2, integer = 5)
	private double totalPrice;
	private Date orderDate;
	@NotEmpty(message = "Order should contain atleast one item")
	private Set<MyItem> items;
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Set<MyItem> getItems() {
		return items;
	}
	public void setItems(Set<MyItem> items) {
		this.items = items;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	
	
}
