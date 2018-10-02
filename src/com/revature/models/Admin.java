package com.revature.models;

public class Admin extends Empolyee implements Transactionable {

	public Admin(String username, String password, String firstName, String lastName) {
		super(username, password, firstName, lastName);
	}

	public boolean cancelAccount(Customer customer, Account account) {
		return false;
	}

	@Override
	public boolean depositMoney(Account account, double amount) {
		double currBalance = account.getBalance();
		
		if (amount < 0) {
		  System.out.println("AMOUNT CANNOT BE NEGATIVE!");
	      return false;
	  }
	        
	  account.setBalance(currBalance + amount);
	  return true;
	}

	@Override
	public boolean withdrawMoney(Account account, double amount) {
		double currBalance = account.getBalance();
		if (amount < 0) {
            System.out.println("AMOUNT CANNOT BE NEGATIVE!");
            return false;
        } else 
        if (amount > account.getBalance()) {
            System.out.println("AMOUNT CANNOT EXCEED ACCOUNT BALANCE!");
            return false;
        }
        
        account.setBalance(currBalance - amount);
        return true;
	}

	@Override
	public boolean transferMoney(Account sourceAccount, Account destAccount, double amount) {
		if(withdrawMoney(sourceAccount, amount)) {
			return depositMoney(destAccount, amount);
		}
    	
    	return false;
	}
}
