package com.gcu.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserProfile extends User {
	@NotNull(message="Name cannot be null")
	@Size(min=3, max=30, message="Name must be between 3 and 30 characters")
	private String firstName, lastName;
	
	@NotNull(message="Information cannot be null")
	@Size(min=1, max=30, message="Information must be between 1 and 30 characters")
	private String email, phoneNumber;
	
	@NotNull(message="Information cannot be null")
	@Size(min=1, max=30, message="Information must be between 1 and 30 characters")
	private String dateOfBirth, address;
	
	/**
	 * Default constructor
	 */
	public UserProfile() {
		super();
		firstName = "";
		lastName = "";
		email = "";
		phoneNumber = "";
		dateOfBirth = "";
		address = "";
	}
	
	/**
	 * Construct new UserProfile with username/password of User
	 * @param user
	 */
	public UserProfile(User user) {
		super(user);
		firstName = "";
		lastName = "";
		email = "";
		phoneNumber = "";
		dateOfBirth = "";
		address = "";
	}
	
	/**
	 * Constructor that takes in a username and password and puts them into 
	 *   their respective parameters
	 *   
	 * @param username
	 * @param password
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param phoneNumber
	 * @param dateOfBirth
	 * @param address
	 */
	public UserProfile(String username, String password, String firstName, String lastName, String email, String phoneNumber, 
			String dateOfBirth, String address) {
		super(username, password);
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.dateOfBirth = dateOfBirth;
		this.address = address;
	}
	
	/**
	 * @return username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Sets username of UserProfile
	 * 
	 * @param username
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
	 * Sets password of UserProfile
	 * 
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets firstName of UserProfile
	 * 
	 * @param firstName
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets lastName of UserProfile
	 * 
	 * @param lastName
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets email of UserProfile
	 * 
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * Sets phoneNumber of UserProfile
	 * 
	 * @param phoneNumber
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return dateOfBirth
	 */
	public String getDateOfBirth() {
		return dateOfBirth;
	}

	/**
	 * Sets dateOfBirth of UserProfile
	 * 
	 * @param dateOfBirth
	 */
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	/**
	 * @return address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Sets address of UserProfile
	 * 
	 * @param address
	 */
	public void setAddress(String address) {
		this.address = address;
	}
}
