package com.practice.CabBookingSystem.repository;

import org.springframework.data.repository.CrudRepository;

import com.practice.CabBookingSystem.model.Location;

public interface LocationRepository extends CrudRepository<Location, Integer> {

}
