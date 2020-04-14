package com.project.ResturantApp.model;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;

public class MyItem {

	@Digits(message = "Item id should be provided in the item", fraction = 0, integer = 500)
	private int itemID;
	@NotBlank(message = "Item name should be provided in the item")
	private String itemName;
	@Digits(message = "Item count should be provided in the item", fraction = 0, integer = 2)
	private int count;
	@Digits(message = "Item price should be added in the order", fraction = 2, integer = 3)
	private double price;
	
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getItemID() {
		return itemID;
	}
	public void setItemID(int itemID) {
		this.itemID = itemID;
	}
	
	
}
