package com.revature.models;

public class Customer extends User implements Transactionable {
	// personal info
    private int phone; 
    private String email = new String();

    public Customer(String username, String password, String firstName, String lastName) {
		super(username, password, firstName, lastName);
	}

    void setFirstName(String name) {
        this.firstName = name;
    }

    void setLastName(String name) {
        this.lastName = name;
    }

    void setPhone(int phoneNumber) {
        this.phone = phoneNumber;
    }

    void setEmail(String email) {
        this.email = email;
    }

    String getInfo() {
        String info = new String();

        info = "****** CUSTOMER INFORMATION ******\n"
                + "Username: " + username + "\n"
                + "Password: " + password + "\n"
                + "First Name: " + firstName + "\n"
                + "Last Name: " + lastName + "\n"
                + "Phone Number: " + phone + "\n"
                + "Email: " + email + "\n\n";

        return info;
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