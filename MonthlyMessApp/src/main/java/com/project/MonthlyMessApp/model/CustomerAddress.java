package com.project.MonthlyMessApp.model;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;

public class CustomerAddress {

	@Digits(message = "House No should be provided in address", fraction = 0, integer = 3)
	private int houseNo;
	@NotBlank(message = "Locality details should be provided in address")
	private String locality;
	@NotBlank(message = "City name should be mentioned in address")
	private String city;
	public int getHouseNo() {
		return houseNo;
	}
	public void setHouseNo(int houseNo) {
		this.houseNo = houseNo;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getLocality() {
		return locality;
	}
	public void setLocality(String locality) {
		this.locality = locality;
	}
	
	
}
