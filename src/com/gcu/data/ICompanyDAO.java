package com.gcu.data;

import java.util.List;

import com.gcu.model.Company;

public interface ICompanyDAO {
	public int createCompany(Company company);

	public int findByCompany(Company company);
}
