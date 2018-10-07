package com.revature.models;

import com.revature.dao.*;

public class Empolyee extends User {
	private boolean isAdmin;

	public Empolyee(String username, String password, String firstName, String lastName, boolean isAdmin) {
		super(username, password, firstName, lastName);
		this.isAdmin = isAdmin;
	}
	
	public Empolyee(String username, String password, String firstName, String lastName) {
		super(username, password, firstName, lastName);
		this.isAdmin = false;
	}
	
	public boolean isAdmin() {
		return isAdmin;
	}
	
	public void setAdmin(boolean isAdmin) {
	    this.isAdmin = isAdmin;
	}
}
