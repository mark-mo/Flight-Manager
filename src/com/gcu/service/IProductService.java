package com.gcu.service;

import java.util.List;

import com.gcu.model.Flight;

public interface IProductService {
	public int create(Flight flight, int companyID);

	public List<Flight> viewFlights();
	
	public Flight viewFlight(int id);
	
	public void update(Flight flight);
	
	public void delete(int id);
}
