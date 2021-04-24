package com.practice.CabBookingSystem.service;

import java.util.Optional;

import com.practice.CabBookingSystem.model.Driver;

public interface DriverService {

	public Driver addDriver(Driver driver);
	public Optional<Driver> getDriver(Integer id);
}
