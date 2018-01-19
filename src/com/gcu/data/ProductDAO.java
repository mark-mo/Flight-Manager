package com.gcu.data;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.gcu.exception.DataNotFoundException;
import com.gcu.model.Flight;
import com.gcu.model.User;

public class ProductDAO implements IProductDAO {
	private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;
    
	@Override
	public int createFlight(Flight product, int companyID) {
		String sql = "INSERT INTO FLIGHT_MANAGER.FLIGHTS(DEPARTURE_CITY, ARRIVAL_CITY, DEPARTURE_TIME, ARRIVAL_TIME, SEATS_FILLED, " + 
				"PLANE_ID, COMPANY_ID) VALUES(?,?,?,?,?,?,?)";
		
		// Insert Flight and get last inserted ID
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplateObject.update(
			new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
					PreparedStatement ps = connection.prepareStatement(sql, new String[] {"ID"});
					ps.setString(1, product.getDepCity());
					ps.setString(2, product.getArrCity());
					ps.setString(3, product.getDepTime());
					ps.setString(4, product.getArrTime());
					ps.setInt(5, 0); // TODO SEATS_FILLED
					ps.setInt(6, Integer.parseInt(product.getPlane()));
					ps.setInt(7, companyID);
					return ps;
				}
			},
			keyHolder);
		
		int id = ((BigDecimal) keyHolder.getKey()).intValueExact();
		return id;
	}

	// TODO not currently accessible through front-end, won't work if tried. Refer to UserDAO
	@Override
	public Flight findByID(int id) {
		Flight flight;
		String sql = "SELECT * FROM FLIGHT_MANAGER.FLIGHTS " +
			"INNER JOIN FLIGHT_MANAGER.PLANES ON FLIGHT_MANAGER.FLIGHTS.PLANE_ID = FLIGHT_MANAGER.PLANES.ID " + 
			"WHERE FLIGHT_MANAGER.FLIGHTS.ID = ?";
		
	    SqlRowSet srs = jdbcTemplateObject.queryForRowSet(sql, id);
	    if (srs.next())
	    {
	    		flight = new Flight(
	    			srs.getInt("ID"),
	    			srs.getString("DEPARTURE_CITY"),
	    			srs.getString("DEPARTURE_TIME"),
	    			srs.getString("ARRIVAL_CITY"),
	    			srs.getString("ARRIVAL_TIME"),
	    			srs.getString("NAME")
	    		);
	    	}
	    else
	    {
	    		throw new DataNotFoundException();
	    }
	    
	    return flight;
	}
	
	@Override
	public List<Flight> findFlights() {
		String sql = "SELECT * FROM FLIGHT_MANAGER.FLIGHTS " +
				"INNER JOIN FLIGHT_MANAGER.PLANES ON FLIGHT_MANAGER.FLIGHTS.PLANE_ID = FLIGHT_MANAGER.PLANES.ID ";
		List<Flight> output = new ArrayList<Flight>();

		SqlRowSet srs = jdbcTemplateObject.queryForRowSet(sql);
		while (srs.next()) {
			output.add(new Flight(
				srs.getInt("ID"),
				srs.getString("DEPARTURE_CITY"),
				srs.getString("DEPARTURE_TIME"),
				srs.getString("ARRIVAL_CITY"),
				srs.getString("ARRIVAL_TIME"),
				srs.getString("NAME")
			));
		}
		return output;
	}
	
	@Override
	public void updateFlight(Flight flight) {
		String sql = "UPDATE FLIGHT_MANAGER.FLIGHTS SET DEPARTURE_TIME = ?, ARRIVAL_TIME = ? WHERE ID = ?";
		
		int rows = jdbcTemplateObject.update(sql, flight.getDepTime(), flight.getArrTime(), flight.getId());
		
		if (rows < 1) {
			throw new DataNotFoundException();
		}
	}

	@Override
	public void deleteFlight(int id) {
		String sql = "DELETE FROM FLIGHT_MANAGER.FLIGHTS WHERE ID = ?";
		
		int rows = jdbcTemplateObject.update(sql, id);
		
		if (rows < 1) {
			throw new DataNotFoundException();
		}
	}
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}
}
