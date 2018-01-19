package com.gcu.data;

import java.util.List;

import com.gcu.model.Flight;

public interface IProductDAO {
	public int createFlight(Flight flight, int companyID);

	public Flight findByID(int id);
	
	public List<Flight> findFlights();
	
	public void updateFlight(Flight flight);
	
	public void deleteFlight(int id);
}
