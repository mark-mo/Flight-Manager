package com.gcu.model;

public class Ticket {
	private Flight flight;
	private double price;
	private String seatNumber;

	/**
	 * 
	 */
	public Ticket() {
		setFlight(new Flight());
		setPrice(0.00);
		setSeatNumber("");
	}
	
	public Ticket(Flight flight, double price, String seatNumber) {
		
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(String seatNumber) {
		this.seatNumber = seatNumber;
	}
}
