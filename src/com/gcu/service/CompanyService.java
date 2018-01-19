package com.gcu.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.gcu.data.ICompanyDAO;
import com.gcu.model.Company;
import com.gcu.model.CompanyToken;
import com.gcu.model.UserToken;

public class CompanyService implements ICompanyService {
	ICompanyDAO dao;

	@Override
	public CompanyToken authenticate(Company company) {
		int id = dao.findByCompany(company);
		CompanyToken token = new CompanyToken(id, company);
		return token;
	}

	@Override
	public CompanyToken register(Company company) {
		int id = dao.createCompany(company);
		CompanyToken token = new CompanyToken(id, company);
		return token;
	}
	
	@Autowired
	public void setCompanyService(ICompanyDAO dao) {
		this.dao = dao;
	}

}
