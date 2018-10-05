package com.revature.models;


public class Account {
	private long number = 0;
    private String name = new String();
    private double balance = 0.00;
    private boolean isApproved;
    
    // used for pulling from database
    public Account(long number, String name, double balance, boolean isApproved) {
		super();
		this.number = number;
		this.name = name;
		this.balance = balance;
		this.isApproved = isApproved;
	}
    
    // used to initially create unapproved account
    public Account(long number, String name, double balance) {
		super();
		this.number = number;
		this.name = name;
		this.balance = balance;
		this.isApproved = false;
	}
   
    // used when initially creating account
	public Account(String name) {
		super();
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