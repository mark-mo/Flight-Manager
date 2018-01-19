package com.gcu.data;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.gcu.exception.AlreadyRegisteredException;
import com.gcu.exception.BadLoginException;
import com.gcu.model.Company;
import com.gcu.model.User;

public class CompanyDAO implements ICompanyDAO {
	private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;
    
	@Override
	public int createCompany(Company company) {
		// TODO. Reference createUser in UserDAO. This is not accessible through front-end at all yet
		
		return 0;
	}

	@Override
	public int findByCompany(Company company) {
		String sql = "SELECT * FROM FLIGHT_MANAGER.COMPANIES WHERE USERNAME = ? AND PASSWORD = ?";

		SqlRowSet srs = jdbcTemplateObject.queryForRowSet(sql, company.getUsername(), company.getPassword());
		if (srs.next()) {
			int id = srs.getInt("ID");
			return id;
		} else {
			throw new BadLoginException();
		}
	}
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}
}
