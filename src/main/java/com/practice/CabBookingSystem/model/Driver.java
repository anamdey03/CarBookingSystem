package com.practice.CabBookingSystem.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Driver {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotBlank(message = "Name should not be blank")
	private String name;
	
	@Email(message = "Email should be in correct format")
	@NotBlank(message = "Email should not be blank")
	@Column(unique = true)
	private String email;
	
	@NotBlank(message = "Phone Number should not be blank")
	@JsonProperty("phone_number")
	@Column(unique = true, columnDefinition = "BIGINT")
	@Pattern(regexp="(^$|[0-9]{10})", message = "Phone Number should be numbers and of 10 digits")
	private String phoneNumber;
	
	@NotBlank(message = "License Number should not be blank")
	@JsonProperty("license_number")
	@Column(unique = true)
	private String licenseNumber;
	
	@NotBlank(message = "Car Number should not be blank")
	@JsonProperty("car_number")
	@Column(unique = true)
	private String carNumber;
	
	@OneToOne(mappedBy = "driver", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private Location location;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getLicenseNumber() {
		return licenseNumber;
	}

	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}

	public String getCarNumber() {
		return carNumber;
	}

	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
	
}
