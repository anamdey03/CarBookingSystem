package com.practice.CabBookingSystem.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice.CabBookingSystem.model.Driver;
import com.practice.CabBookingSystem.repository.DriverRepository;	

@Service
public class DriverServiceImpl implements DriverService {
	
	@Autowired
	private DriverRepository driverRepository;

	@Override
	public Driver addDriver(Driver driver) {
		return driverRepository.save(driver);
	}

	@Override
	public Optional<Driver> getDriver(Integer id) {
		return driverRepository.findById(id);
	}
}
