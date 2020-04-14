package com.project.ResturantApp.model;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;

public class MyMenu {

	private int itemId;
	@NotBlank(message = "Item name should be provided inside a menu")
	private String itemName;
	@Digits(message = "Item price should be provided inside a menu", integer = 3, fraction = 2)
	private double price;
	@NotBlank(message = "Item image path should be provided inside a menu")
	private String imgPath;
	
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	
	
}
