package com.revature.models;

public class User {
	// user info
	protected String username = new String();
	protected String password = new String();
	protected String firstName = new String();
	protected String lastName = new String();
	protected String userID = new String();

	public User(String username, String password, String firstName, String lastName) {
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userID = ""; // add random id generator later
	}

}
