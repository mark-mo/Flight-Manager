package com.gcu.model;

public class CompanyToken {
	private int id;
	private Company company;
	
	public CompanyToken() {
		this.id = -1;
		this.company = new Company();
	}
	
	public CompanyToken(int id, Company company) {
		this.id = id;
		this.company = company;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
	
	
}
