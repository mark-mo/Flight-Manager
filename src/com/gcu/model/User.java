package com.gcu.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class User {
	@NotNull(message="Username cannot be null")
	@Size(min=1, max=30, message="Username must be between 1 and 30 characters")
	protected String username;
	
	@NotNull(message="Password cannot be null")
	@Size(min=1, max=30, message="Password must be between 1 and 30 characters")
	protected String password;
	
	/**
	 * Default Constructor
	 */
	public User() {
		username = "";
		password = "";
	}
	
	/**
	 * Copy constructor
	 */
	public User(User user) {
		username = user.getUsername();
		password = user.getPassword();
	}
	
	/**
	 * Constructor that takes in a username and password and puts them into 
	 *   their respective parameters
	 *   
	 * @param username
	 * @param password
	 */
	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	/**
	 * @return username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Sets username of User
	 * 
	 * @param firstName
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets password of User
	 * 
	 * @param lastName
	 */
	public void setPassword(String password) {
		this.password = password;
	}
}
