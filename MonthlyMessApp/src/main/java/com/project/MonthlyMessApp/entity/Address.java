package com.project.MonthlyMessApp.entity;

import javax.persistence.Embeddable;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;

@Embeddable
public class Address {

	@Digits(message = "House no cant be empty in mess entity", fraction = 0, integer = 3)
	private int houseNo;
	@NotBlank(message = "Locality details cant be empty in mess entity")
	private String locality;
	@NotBlank(message = "City name cant be empty in mess entity")
	private String city;
	public int getHouseNo() {
		return houseNo;
	}
	public void setHouseNo(int houseNo) {
		this.houseNo = houseNo;
	}
	public String getLocality() {
		return locality;
	}
	public void setLocality(String locality) {
		this.locality = locality;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	
}
