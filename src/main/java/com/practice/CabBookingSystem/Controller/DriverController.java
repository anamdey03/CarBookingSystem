package com.practice.CabBookingSystem.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.xml.bind.ValidationException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.practice.CabBookingSystem.model.AvailableCabs;
import com.practice.CabBookingSystem.model.Driver;
import com.practice.CabBookingSystem.model.Location;
import com.practice.CabBookingSystem.service.DriverService;
import com.practice.CabBookingSystem.service.LocationService;
import com.practice.CabBookingSystem.util.ErrorMessage;
import com.practice.CabBookingSystem.util.Message;

@RestController
@RequestMapping(value = "/api/v1/driver")
public class DriverController {

	@Autowired
	private DriverService driverService;
	
	@Autowired
	private LocationService locationService;
	
	@PostMapping("/register")
	@ResponseStatus(HttpStatus.CREATED)
	public Driver addDriver(@Valid @RequestBody Driver driver) {
		return driverService.addDriver(driver);
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	List<ErrorMessage> exceptionHandler(MethodArgumentNotValidException e) {
		List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
		List<ErrorMessage> fieldErrorMessages = fieldErrors.stream()
					.map(fieldError -> new ErrorMessage("failure", fieldError.getDefaultMessage()))
					.collect(Collectors.toList());
		return fieldErrorMessages;
	}
	
	@PostMapping("/{id}/sendLocation")
	@ResponseBody
	public ResponseEntity<Map<String, String>> addLocation(@PathVariable Integer id, @Valid @RequestBody Location location) {
		Map<String, String> response = new HashMap<String, String>();
		Optional<Driver> driver = driverService.getDriver(id);
		if(driver.isPresent()) { 
			location.setDriver(driver.get());
			driver.get().setLocation(location);
			driverService.addDriver(driver.get());
			response.put("status", "success");
			return new ResponseEntity<Map<String, String>>(response, HttpStatus.ACCEPTED);
		}
		response.put("status", "Driver Not Found");
		return new ResponseEntity<Map<String, String>>(response, HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/available_cabs")
	@ResponseBody
	public ResponseEntity<List<AvailableCabs>> getAvailableCabs(@Valid @RequestBody Location location) throws ValidationException {
		List<AvailableCabs> availableCabs =  locationService.getAllAvaliableCabsLocations(location);
		if(!availableCabs.isEmpty()) {
			return new ResponseEntity<List<AvailableCabs>>(availableCabs, HttpStatus.OK);
		}
		else {
			throw new ValidationException("No Cabs Available");
		}
	}
	
	@ResponseStatus(HttpStatus.OK)
	@ExceptionHandler(ValidationException.class)
	Message exceptionHandler(ValidationException e) {
		return new Message(e.getMessage());
	}
	
	@PutMapping("/driver/{id}")
	@ResponseBody
	public ResponseEntity<Driver> updateDriver(@PathVariable Integer id, @Valid @RequestBody Driver driver) {
		if(driverService.getDriver(id).isPresent()) {
			Driver existingDriver = driverService.getDriver(id).get();
			BeanUtils.copyProperties(driver, existingDriver, "id");
			return new ResponseEntity<Driver>(driverService.addDriver(existingDriver), HttpStatus.OK);
		}
		else
			return new ResponseEntity<Driver>(driver, HttpStatus.BAD_REQUEST);
	}
	
}
