package com.practice.CabBookingSystem.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice.CabBookingSystem.model.AvailableCabs;
import com.practice.CabBookingSystem.model.Location;
import com.practice.CabBookingSystem.repository.LocationRepository;

@Service
public class LocationServiceImpl implements LocationService {

	@Autowired
	private LocationRepository locationRepository;

	@Override
	public List<AvailableCabs> getAllAvaliableCabsLocations(Location location) {
		Iterable<Location> locations = locationRepository.findAll();
		List<Location> loc = new ArrayList<>();
		locations.forEach(loc::add);
		return findNearestCabs(loc, location);
	}
	
	static List<AvailableCabs> findNearestCabs(List<Location> locations, Location passengerLocation) {
		List<AvailableCabs> availableCabs = new ArrayList<>();
		for(Location driverLocation: locations ) {
			AvailableCabs availableCab = new AvailableCabs();
			// distance between latitudes and longitudes
			double dLatitude = Math.toRadians(driverLocation.getLatitude() - passengerLocation.getLatitude());
			double dLongitude = Math.toRadians(driverLocation.getLongitude() - passengerLocation.getLongitude());
			
			// convert to radians
	        double passengerLatitude = Math.toRadians(passengerLocation.getLatitude());
	        double driverLatitude = Math.toRadians(driverLocation.getLatitude());
	        
	        // apply formulae
	        double a = Math.pow(Math.sin(dLatitude / 2), 2) + 
	                   Math.pow(Math.sin(dLongitude / 2), 2) * 
	                   Math.cos(passengerLatitude) * 
	                   Math.cos(driverLatitude);
	        double rad = 6371;
	        double c = 2 * Math.asin(Math.sqrt(a));
	        double distance = rad * c;
	        
	        if(distance <= 4) {
	        	availableCab.setName(driverLocation.getDriver().getName());
	        	availableCab.setPhoneNumber(driverLocation.getDriver().getPhoneNumber());
	        	availableCab.setCarNumber(driverLocation.getDriver().getCarNumber());
	        	availableCabs.add(availableCab);
	        }
		}
		return availableCabs;
	}
	
	
}	
