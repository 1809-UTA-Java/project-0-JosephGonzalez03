package com.revature.models;

public class Customer extends User {
	// personal info
    private int phone; 
    private String email = new String();

    public Customer(String username, String password, String firstName, String lastName, int phone, String email) { 
    	super(username, password, firstName, lastName);
    	this.phone = phone;
    	this.email = email;
    }
    
    public Customer(String username, String password, String firstName, String lastName) {
		super(username, password, firstName, lastName);
	}

    public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}