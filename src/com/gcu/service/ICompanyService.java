package com.gcu.service;

import com.gcu.model.Company;
import com.gcu.model.CompanyToken;

public interface ICompanyService {
	public CompanyToken authenticate(Company company);

	public CompanyToken register(Company company);
}
