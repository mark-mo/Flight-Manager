package com.gcu.data;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.gcu.exception.AlreadyRegisteredException;
import com.gcu.exception.BadLoginException;
import com.gcu.exception.DataNotFoundException;
import com.gcu.model.User;
import com.gcu.model.UserProfile;

public class UserDAO implements IUserDAO {
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;

	@Override
	public int createUser(UserProfile user) {
		// Find if user exists
		String uniqueSql = "SELECT COUNT(*) FROM FLIGHT_MANAGER.USERS WHERE USERNAME = ?";
		int uniqueRowsCount = jdbcTemplateObject.queryForObject(uniqueSql, new Object[] { user.getUsername() },
				Integer.class);
		if (uniqueRowsCount > 0) {
			throw new AlreadyRegisteredException();
		}

		// Insert User Profile and get last inserted ID
		String sqlProfile = "INSERT INTO FLIGHT_MANAGER.USER_PROFILES(FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, DATE_OF_BIRTH, ADDRESS) VALUES(?,?,?,?,?,?)";

		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplateObject.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(sqlProfile, new String[] { "ID" });
				ps.setString(1, user.getFirstName());
				ps.setString(2, user.getLastName());
				ps.setString(3, user.getEmail());
				ps.setString(4, user.getPhoneNumber());
				ps.setString(5, user.getDateOfBirth());
				ps.setString(6, user.getAddress());
				return ps;
			}
		}, keyHolder);

		int profileID = ((BigDecimal) keyHolder.getKey()).intValueExact();

		// Insert User and get last inserted ID
		String sqlUser = "INSERT INTO FLIGHT_MANAGER.USERS(USERNAME, PASSWORD, USER_PROFILE_ID) VALUES(?,?,?)";

		jdbcTemplateObject.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(sqlUser, new String[] { "ID" });
				ps.setString(1, user.getUsername());
				ps.setString(2, user.getPassword());
				ps.setInt(3, profileID);
				return ps;
			}
		}, keyHolder);

		int userID = ((BigDecimal) keyHolder.getKey()).intValueExact();

		return userID;
	}

	@Override
	public int findByUser(User user) {
		String sql = "SELECT * FROM FLIGHT_MANAGER.USERS WHERE USERNAME = ? AND PASSWORD = ?";

		SqlRowSet srs = jdbcTemplateObject.queryForRowSet(sql, user.getUsername(), user.getPassword());
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
