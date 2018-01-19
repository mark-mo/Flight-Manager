package com.gcu.model;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

public class Flight {
	int id;
	@NotNull
	private String depCity, arrCity;
	@NotNull
	private String depTime, arrTime;
	@NotNull
	private String plane;
	
	public Flight() {
		id = -1;
		depCity = "";
		depTime = "";
		arrCity = "";
		arrTime = "";
		plane = "";
	}
	
	public Flight(String depCity, String depTime, String arrCity, String arrTime, String plane) {
		this.depCity = depCity;
		this.depTime = depTime;
		this.arrCity = arrCity;
		this.arrTime = arrTime;
		this.plane = plane;
	}
	
	public Flight(int id, String depCity, String depTime, String arrCity, String arrTime, String plane) {
		this.id = id;
		this.depCity = depCity;
		this.depTime = depTime;
		this.arrCity = arrCity;
		this.arrTime = arrTime;
		this.plane = plane;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDepCity() {
		return depCity;
	}

	public void setDepCity(String depCity) {
		this.depCity = depCity;
	}

	public String getDepTime() {
		return depTime;
	}

	public void setDepTime(String depTime) {
		this.depTime = depTime;
	}

	public String getArrCity() {
		return arrCity;
	}

	public void setArrCity(String arrCity) {
		this.arrCity = arrCity;
	}

	public String getArrTime() {
		return arrTime;
	}

	public void setArrTime(String arrTime) {
		this.arrTime = arrTime;
	}

	public String getPlane() {
		return plane;
	}

	public void setPlane(String plane) {
		this.plane = plane;
	}
}
