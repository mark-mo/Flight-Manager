package com.gcu.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gcu.model.Flight;
import com.gcu.service.IProductService;

@RestController
@RequestMapping("/service1")
public class FlightRest {
	@Autowired
	IProductService service;
	
	// Spring Rest service to return all valid flights in the database.
	@GetMapping("/flights")
	public List<Flight> getFlights() {
		List<Flight> flights = service.viewFlights();

		return flights;
	}
}
