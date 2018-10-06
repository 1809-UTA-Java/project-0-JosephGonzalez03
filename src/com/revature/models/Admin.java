package com.revature.models;

public class Admin extends Empolyee implements Transactionable {

	public Admin(String username, String password, String firstName, String lastName) {
		super(username, password, firstName, lastName);
	}
}
