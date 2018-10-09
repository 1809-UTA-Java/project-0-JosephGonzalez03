package com.revature;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.revature.duo.CustomerDUO;
import com.revature.models.Account;
import com.revature.models.Customer;
import com.revature.util.DUOUtil;

public class CustomerPage implements Viewable{
	CustomerDUO cDUO = DUOUtil.getCustomerDUO();
	
	@Override
	public String optionsMenu(Scanner scan) {
    	Menus.pageHeader("ACCOUNT MANAGEMENT PAGE");
        toConsole("What would you like to do?");
        toConsole("1. CREATE a new account");
        toConsole("2. ACCESS an existing account");
        toConsole("3. LOGOUT");
        toConsole("Choose action: ");
        
        return scan.nextLine();
    }

	public Account createAccountMenu(Scanner scan, Customer c) {
    	String resp = new String();
    	
    	List<Account> accounts = DUOUtil.getCustomerDUO().getAccounts(c.getUsername());
    	boolean sameName = false;
    	do {
    		Menus.pageHeader("CREATE BANK ACCOUNT PAGE");
        	toConsole("Enter fields for new account: ");
        	toConsole("Account Name: ");
        	resp = scan.nextLine();
        	
        	// ensure account with name doesn't already exist
        	for(Account a : accounts) {
        		if(resp.contentEquals(a.getName())) {
        			sameName = true;
        			System.out.println("WARNING: Account with name \"" + 
        			resp + "\" already exists! Please choose a different name.");
        		}
        	}
    	} while(!sameName);
    	
    	toConsole("\nNew account" + resp + "created!");
    	
    	return new Account(resp);
    }

	@Override
	public void customerProfileMenu() {
		toConsole("OPTIONS: ");
    	toConsole("1. DEPOSIT [amount] [dest. account name]");
    	toConsole("2. WITHDRAW [amount] [source account name]");
    	toConsole("3. TRANSFER [amount] [source account name]"
    								 + "[dest. account name]");
    	toConsole("BACK to previous page");
    	toConsole("Choose action: ");
	}
	
	private Account findAccount(String customer_username, String account_name) {
		List<Account> accounts = cDUO.getAccounts(customer_username);
		Account tempAccount = null;
		
		for(Account a : accounts) {
			if (account_name.contentEquals(a.getName())) {
				if(a.isApproved()) {
				    tempAccount = a;
				} else {
				    System.out.println("Account: " + account_name + "is currently not aproved. Please wait 1 business day for new accounts to be approved.\n");
				}
				break;
			}
		}
		return tempAccount;
	}
	
	@Override
	public boolean customerProfile(Scanner scan, String customer_username) {
    	CustomerDUO cDUO = DUOUtil.getCustomerDUO();
    	List<Account> accounts = cDUO.getAccounts(customer_username);
    	
    	String key = "";
    	
    	Menus.pageHeader("ACCOUNT LEDGER");
    	
    	if (accounts == null) {
    		System.out.println("You currently have no accoounts. Return to previous" +
    							"\npage to create some.");
    		toConsole("\nOPTIONS: ");
    		toConsole("BACK to previous page");
	    	toConsole("Choose action: ");
			key = scan.next().toUpperCase();
			
			switch (Menus.toAction(key)) {
			case BACK:
				return false;
			default:
				break;
			}
    	} else { // allow customer to perform transactions
    		// display customer's accounts
    		Menus.tableHeader("ACCOUNTS TABLE");
    		accounts.forEach(a -> Menus.displayAccountContents(a));
    		customerProfileMenu();
    
    		// save user inputs for validation
			double amount = 0;
			String name1 = "";
			String name2 = "";
			Account currA = null, destA = null;
			boolean validInput = false;
    		
    		// loop until valid command is entered
        	do {
        		key = scan.next();
    			
        		// break down user input to variables
    			try {
    				switch(Menus.toAction(key)) {
    				case DEPOSIT:
    				case WITHDRAW:
    					amount = scan.nextDouble();
    					name1 = scan.next();
    					name2 = "";
    					currA = findAccount(customer_username, name1);
    					
    					validInput = currA == null ? false : true;
    					break;
    				case TRANSFER:
    					amount = scan.nextDouble();
    					name1 = scan.next();
    					name2 = scan.next();
    					currA = findAccount(customer_username, name1);
        				destA = findAccount(customer_username, name2);
    					
        				validInput = (currA == null) || (destA == null) ? false : true;
    					break;
    				case BACK:
    					return false;
    				default:
    					System.out.println("INVALID KEYWORD ENTERED!");
    				}
    			} catch (InputMismatchException e) {
    				e.getMessage();
    				validInput = false;
    				System.out.println("INVALID INPUT IN COMMAND!\n");
    			} 	
    		} while(!validInput);
    		
        	// execute command
        	switch (Menus.toAction(key)) {
    		case DEPOSIT:
    			cDUO.depositMoney(currA, amount);
    			break;
    		case WITHDRAW:
    			cDUO.withdrawMoney(currA, amount);
    			break;
    		case TRANSFER:
    			cDUO.transferMoney(currA, destA, amount);
    		default:
    			break;
    		}
    	}
    	return true;
    }
}
