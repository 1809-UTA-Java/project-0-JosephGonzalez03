package com.revature.portals;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.revature.duo.EmployeeDUO;
import com.revature.models.Account;
import com.revature.models.Customer;
import com.revature.util.DUOUtil;

public class EmployeePage implements Viewable {
	EmployeeDUO eDUO = DUOUtil.getEmployeeDUO();
	
	@Override
	public String optionsMenu(Scanner scan) {
    	Menus.pageHeader("USER & ACCOUNT MANAGEMENT PAGE");
        toConsole("What would you like to do?");
        toConsole("1. SHOW [customers / accounts]");
        toConsole("2. GET [customer username]");
        toConsole("3. LOGOUT");
        toConsole("Choose action: ");
        
        return scan.nextLine();
    }
	
	public void showCustomersOrAccounts(String option) {
		String key = option.toUpperCase();
		
		EmployeeDUO eDUO = DUOUtil.getEmployeeDUO();
		
		switch (key) {
		case "CUSTOMERS":
			List<Customer> customers = eDUO.getCustomers();
			Menus.customersTable(customers);
			break;
		case "ACCOUNTS":
			List<Account> accounts = eDUO.getAccounts();
			Menus.accountsTable(accounts);
			break;
		default:
			System.out.println("INVALID OPTION! PLEASE CHOOSE EITHER CUSTOMERS OR ACCOUNTS!\n");
			break;
		}
	}

	@Override
	public void customerProfileMenu() {
		toConsole("OPTIONS: ");
		toConsole("1. APPROVE [account number]");
		toConsole("2. DENY [account number]");
		toConsole("3. BACK to previous page");
		toConsole("Choose action: ");
	}
	
	@Override
	public boolean customerProfile(Scanner scan, String customer_username) {
		Customer customer = eDUO.getCustomer(customer_username);
		List<Account> accounts = eDUO.getAccounts(customer_username);
		
		String key;
		
		if (customer == null) {
			System.out.println("INVALID USERNAME!\n");
			return false;
		} else {
      		// save user inputs for validation
      		Account currA =  null;
      		boolean validInput = false;
      		
    		// loop until valid command is entered
      		do {
      			// display user's & accounts' info
      			Menus.customerTable(customer);
    			Menus.accountsTable(accounts);
    			
    			customerProfileMenu();
          		key = scan.next();
          		
      			// catch when user does not input proper account number
      			try {
      				switch (Menus.toAction(key)) {
      				case APPROVE:
      				case DENY:
	      				int accountNumber = scan.nextInt();
	      				currA = eDUO.getAccount(accountNumber);
	      				
	      				if (currA == null) {
	      	  				System.out.println("INVALID ACCOUNT NUMBER!\n");	
	      	  			} else {
	      	  				validInput = true;
	      	  			}
	      			break;
      				case BACK:
      					return false;
      				default:
      					System.out.println("INVALID KEYWORD ENTERED!");
      					break;
      				}
      			} catch (InputMismatchException e) {
      				e.getMessage();
      				System.out.println("INVALID ACCOUNT NUMBER!\n");
      				validInput = false;
      			}
      		} while(!validInput);	
      		
      		// execute command
      		switch (Menus.toAction(key)) {
				case APPROVE:
					eDUO.approveAccount(currA);
					break;
				case DENY:
					eDUO.denyAccount(currA);
					break;
				default:
					break;
				}
		}
      	return true;
	}
}
