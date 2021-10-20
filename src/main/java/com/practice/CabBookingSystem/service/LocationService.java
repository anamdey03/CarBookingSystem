package com.practice.CabBookingSystem.service;

import java.util.List;

import com.practice.CabBookingSystem.model.AvailableCabs;
import com.practice.CabBookingSystem.model.Location;

public interface LocationService {

	public List<AvailableCabs> getAllAvaliableCabsLocations(Location location);
}
