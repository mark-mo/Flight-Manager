package com.gcu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gcu.data.IProductDAO;
import com.gcu.model.Flight;

public class ProductService implements IProductService {
	IProductDAO dao;

	@Override
	public int create(Flight flight, int companyID) {
		int flightID = dao.createFlight(flight, companyID);
		return flightID;
	}

	@Override
	public List<Flight> viewFlights() {
		return dao.findFlights();
	}
	
	@Override
	public Flight viewFlight(int id) {
		return dao.findByID(id);
	}
	
	@Override
	public void update(Flight flight) {
		dao.updateFlight(flight);
	}

	@Override
	public void delete(int id) {
		dao.deleteFlight(id);
	}

	@Autowired
	public void setProductService(IProductDAO dao) {
		this.dao = dao;
	}
}
