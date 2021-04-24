package com.practice.CabBookingSystem.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AvailableCabs {

	private String name;
	
	@JsonProperty("car_number")
	private String carNumber;
	
	@JsonProperty("phone_number")
	private String phoneNumber;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCarNumber() {
		return carNumber;
	}

	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
}
