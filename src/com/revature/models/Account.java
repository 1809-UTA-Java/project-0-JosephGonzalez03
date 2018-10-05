package com.revature.models;


public class Account {
	private long number = 0;
	private String username = new String();
    private String name = new String();
    private double balance = 0.00;
    private boolean isApproved;
    
    // used for pulling from database
    public Account(long number, String username, String name, double balance, boolean isApproved) {
		super();
		this.number = number;
		this.username = username;
		this.name = name;
		this.balance = balance;
		this.isApproved = isApproved;
	}
    
    // used with account number is know
    public Account(long number, String username, String name, double balance) {
		super();
		this.number = number;
		this.username = username;
		this.name = name;
		this.balance = balance;
		this.isApproved = false;
	}
   
    // used when initially creating account
	public Account(String username, String name) {
		super();
		this.username = username;
		this.name = name;
		this.balance = 0.00;
		this.isApproved = false;
	}
	
	public Account() {
		
	}
	
	public long getNumber() {
		return number;
	}

	public void setNumber(long number) {
		this.number = number;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public boolean isApproved() {
		return isApproved;
	}

	public void setApproved(boolean isApproved) {
		this.isApproved = isApproved;
	}

	public String getInfo() {
        String info = new String();

        info = "****** ACCOUNT INFORMATION ******\n"
                + "Account Name: " + name + "\n"
                + "Account Number: " + number + "\n\n";
        
        return info;
    }
}