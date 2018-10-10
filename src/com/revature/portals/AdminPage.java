package com.revature.portals;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.revature.duo.*;
import com.revature.models.*;
import com.revature.util.DUOUtil;

public class AdminPage implements Viewable {
	CustomerDUO cDUO = DUOUtil.getCustomerDUO();
	EmployeeDUO eDUO = DUOUtil.getEmployeeDUO();
	
	@Override
	public String optionsMenu(Scanner scan) {
    	Menus.pageHeader("USER & ACCOUNT MANAGEMENT PAGE");
        toConsole("What would you like to do?");
        toConsole("1. SHOW [customers / accounts / employees]");
        toConsole("2. GET [customer username]");
        toConsole("3. LOGOUT");
        toConsole("Choose action: ");
        
        return scan.nextLine();
    }

	@Override
	public void customerProfileMenu() {
		toConsole("OPTIONS: ");
    	toConsole("1. DEPOSIT [amount] [dest. account name]");
    	toConsole("2. WITHDRAW [amount] [source account name]");
    	toConsole("3. TRANSFER [amount] [source account name]"
    								 + "[dest. account name]");
    	toConsole("4. APPROVE [account number]");
		toConsole("5. DENY [account number]");
		toConsole("6. CANCEL [account number]");
    	toConsole("BACK to previous page");
    	toConsole("Choose action: ");		
	}
	
	public void showCustomersOrAccountsOrEmployees(String option) {
		String key = option.toUpperCase();
		
		EmployeeDUO eDUO = DUOUtil.getEmployeeDUO();
		
		switch (key) {
		case "CUSTOMERS":
			List<Customer> customers = eDUO.getCustomers();
		
			Menus.customerTableHeader();
			customers.forEach(c -> Menus.displayCustomerProfile(c));
			break;
		case "ACCOUNTS":
			List<Account> accounts = eDUO.getAccounts();
			
			Menus.accountTableHeader();
			accounts.forEach(a -> Menus.displayAccountContents(a));
			break;
		case "EMPLOYEES":
			List<Employee> employees = eDUO.getEmployees();
			
			Menus.employeeTableHeader();
			employees.forEach(e -> Menus.displayEmployeeProfile(e));
			break;
		default:
			System.out.println("INVALID OPTION! PLEASE CHOOSE EITHER CUSTOMERS OR ACCOUNTS!\n");
			break;
		}
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
		Customer customer = eDUO.getCustomer(customer_username);
		List<Account> accounts = eDUO.getAccounts(customer_username);
		
		String key;
		
		if (customer == null) {
			System.out.println("INVALID USERNAME!\n");
			return false;
		} else {

      		// save user inputs for validation
      		double amount = 0;
      		String name1 = "";
      		String name2 = "";
      		Account currA = null, destA = null;
      		int accountNumber;
      		boolean validInput = false;
      		
    		// loop until valid command is entered
      		do {
      			// display user's & accounts' info
      			Menus.customerTableHeader();
    			Menus.displayCustomerProfile(customer);
    			Menus.accountTableHeader();
    			accounts.forEach(a -> Menus.displayAccountContents(a));
    			
    			customerProfileMenu();
          		key = scan.next();
      			
      			// catch when user does not input proper account number
      			// break down user input to variables
      			try {
      				switch (Menus.toAction(key)) {
      				case DEPOSIT:
    				case WITHDRAW:
    					amount = scan.nextDouble();
    					name1 = scan.next();
    					name2 = "";
    					currA = findAccount(customer_username, name1);
    					// test if account was found
    					validInput = currA == null ? false : true;
    					break;
    				case TRANSFER:
    					amount = scan.nextDouble();
    					name1 = scan.next();
    					name2 = scan.next();
    					currA = findAccount(customer_username, name1);
        				destA = findAccount(customer_username, name2);
        				// test if accounts were found
        				validInput = (currA == null) || (destA == null) ? false : true;
    					break;
      				case APPROVE:
      				case DENY:
	      				accountNumber = scan.nextInt();
	      				currA = eDUO.getAccount(accountNumber);
	      				
	      				if (currA == null || currA.isApproved()) {
	      					String message = currA == null ? "INVALID ACCOUNT NUMBER!\n" : "ACCOUNT IS ALREADY APPROVED!\n";
	      	  				System.out.println(message);	
	      	  			} else {
	      	  				validInput = true;
	      	  			}
	      				break;
      				case CANCEL: 
      					accountNumber = scan.nextInt();
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
      			case DEPOSIT:
      				cDUO.depositMoney(currA, amount);
      				break;
      			case WITHDRAW:
      				cDUO.withdrawMoney(currA, amount);
      				break;
      			case TRANSFER:
      				cDUO.transferMoney(currA, destA, amount);
				case APPROVE:
					eDUO.approveAccount(currA);
					break;
				case DENY:
					eDUO.denyAccount(currA);
					break;
				case CANCEL:
					eDUO.cancelAccount(currA);
				default:
					break;
				}
		}
      	return true;
	}
	
}
