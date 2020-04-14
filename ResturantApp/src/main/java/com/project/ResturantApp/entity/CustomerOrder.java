package com.project.ResturantApp.entity;

import java.util.Date;
import java.util.Set;
import java.util.SortedSet;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class CustomerOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int orderId;
	@NotBlank(message = "Customer name should be provided in order entity")
	private String customerName;
	@Digits(message = "Total price should be provided in order entity", fraction = 2, integer = 5)
	private double totalPrice;
	@NotNull(message = "Order date cant be empty in order entity")
	@Temporal(TemporalType.DATE)
	private Date orderDate;
	@ManyToMany(targetEntity = Item.class, cascade = CascadeType.ALL)
	@JoinTable(name = "ORDER_ITEM", 
				joinColumns = {@JoinColumn(name = "ORDER_ID")},
				inverseJoinColumns = {@JoinColumn(name = "ITEM_ID")})
	@NotEmpty(message = "Order should contain atleast one item")
	private Set<Item> items;
	
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
	public Set<Item> getItems() {
		return items;
	}
	public void setItems(Set<Item> items) {
		this.items = items;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	
	
}
