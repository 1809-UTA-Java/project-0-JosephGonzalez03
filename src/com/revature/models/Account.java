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

    double getBalance() {
        return balance;
    }

    String getInfo() {
        String info = new String();

        info = "****** ACCOUNT INFORMATION ******\n"
                + "Account Name: " + name + "\n"
                + "Account Number: " + number + "\n\n";
        
        return info;
    }


}