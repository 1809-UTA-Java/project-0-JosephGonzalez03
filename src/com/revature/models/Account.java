package com.revature.models;

class Account implements Depositable, Withdrawalable {
    String name = new String();
    int number = 00000000;
    double balance = 0.00;

    public Account(String name, int number, double balance) {
        this.name = name;
        this.number = number;
        this.balance = balance;
    }

    boolean depositMoney(double amount) {
        if (amount < 0) {
            System.out.println("AMOUNT CANNOT BE NEGATIVE!");
            return false;
        }
        
        balance += amount;
        return true;
    }

    boolean withdrawMoney(double amount) {
        if (amount < 0) {
            System.out.println("AMOUNT CANNOT BE NEGATIVE!");
            return false;
        } else 
        if (amount > balance) {
            System.out.println("AMOUNT CANNOT EXCEED ACCOUNT BALANCE!");
            return false;
        }
        
        balance -= amount;
        return true;
    }

    boolean getAccountInfo() {
        System.out.println("****** ACCOUNT INFORMATION ******");
        System.out.println("Account Name: " + name);
        System.out.println("Account Number: " + number);
        System.out.println("Account Balance: " + balance + "\n\n");
    }


}