package com.revature.models;


public class Account {
    private String name = new String();
    private String number = new String();
    private double balance = 0.00;
    
    public Account(String name) {
        this.name = name;
        this.number = "random number";
    }

    public String getName() {
    	return name;
    }
    
    public double getBalance() {
        return balance;
    }
    
    public void setBalance(double balance) {
    	this.balance = balance;
    }
    
    public String getAccountNumber() {
    	return number;
    }

    public String getInfo() {
        String info = new String();

        info = "****** ACCOUNT INFORMATION ******\n"
                + "Account Name: " + name + "\n"
                + "Account Number: " + number + "\n\n";
        
        return info;
    }
}