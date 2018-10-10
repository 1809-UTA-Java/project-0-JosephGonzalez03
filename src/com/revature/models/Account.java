package com.revature.models;


public class Account {
	private static int counter = 100000;
	private int number = 0;
    private String name = new String();
    private double balance = 0.00;
    private boolean isApproved;
    
    // used for pulling from database
    public Account(int number, String name, double balance, boolean isApproved) {
		super();
		this.number = number;
		this.name = name;
		this.balance = balance;
		this.isApproved = isApproved;
	}
   
    // used when initially creating account
	public Account(String name) {
		super();
		this.number = ++counter;
		this.name = name;
		this.balance = 0.00;
		this.isApproved = false;
	}
	
	public Account() {
	}
	
	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
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
    
    @Override
     public boolean equals(Object obj) {
        return (this.number == ((Account) obj).getNumber());
	}
}