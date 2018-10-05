package com.revature.models;

import com.revature.dao.*;

public class Empolyee extends User implements Requestable {

	public Empolyee(String username, String password, String firstName, String lastName) {
		super(username, password, firstName, lastName);
	}

	@Override
	public boolean approveAccount(Account account) {
		return false;
	}

	@Override
	public boolean denyAccount(Account account) {
		return false;
	}
	
	public String getCustomerInfo(Customer c) {
		return c.getInfo();
	}
	
	public String getAccountInfo(Account a) {
		return a.getInfo();
	}
	
	public void getAccountBalance(Account a) {
		a.getBalance();
	}

	
}
