package com.revature;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.revature.database.Database;
import com.revature.models.*;

public class Menus {
	
    static void toConsole(String message) {
        System.out.println(message);
    }
    
    static void pageHeader(String pageName) {
    	  toConsole("\n\n\n=====================================");
          toConsole(pageName);
          toConsole("=====================================");
    }

    static String startMenu(Scanner scan) {
        toConsole("Welcome to your Personal Banking Application!\n");
        toConsole("1. REGISTER for a user account");
        toConsole("2. LOGIN fromTo an existing one");
        toConsole("3. EXIT banking app");
        toConsole("Choose action: ");
        
        return scan.nextLine();
    }

    static Customer registerMenu(Scanner scan) {
        List<String> resps = new ArrayList<>();
        String confirmedPassword = new String();

        pageHeader("USER ACCOUNT REGISTRATION");

        toConsole("Please enter user account information.");
        toConsole("Username: ");
        resps.add(scan.nextLine());
        
        // ask for password until password & confirmed password match
        do {
            toConsole("Password: ");
            resps.add(scan.nextLine());

            toConsole("Confirm password: ");
            confirmedPassword = scan.nextLine();
        } while (!resps.get(1).equals(confirmedPassword));
        
        pageHeader("ADDITIONAL USER INFORMATION");
    	toConsole("First Name: ");
    	resps.add(scan.nextLine());
    	toConsole("Last Name: ");
    	resps.add(scan.nextLine());
    	toConsole("Phone Number: ");
    	resps.add(scan.nextLine());
    	toConsole("E-MAIL: ");
    	resps.add(scan.nextLine());
    	
    	toConsole("\n\n\n\n\n\n");
        return new Customer(resps.get(0),resps.get(1),resps.get(2),resps.get(3),resps.get(4),resps.get(5));
    }

    static String loginMenu(Scanner scan) {
    User user = null;
    	List<String> responses = new ArrayList<>();

	boolean isVerified = false;

    	do {
    		pageHeader("LOGIN PAGE");
    		toConsole("Please username and password.");
    		toConsole("Username: ");
    		responses.add(scan.nextLine());
    		toConsole("Password: ");
    		responses.add(scan.nextLine());
    		
    		user = loginVerification(responses);
    		
    		if(user != null) {
		    isVerified = true;
    		}
    				
    	} while(!isVerified);

        return user.getUsername();
    }

    static String userOptionsMenu(Scanner scan) {
    	pageHeader("ACCOUNT MANAGEMENT PAGE");
        toConsole("What would you like to do?");
        toConsole("1. CREATE a new account");
        toConsole("2. ACCESS an existing account");
        toConsole("3. LOGOUT");
        toConsole("Choose action: ");
        
        return scan.nextLine();
        
    }
    
    static Account createAccountMenu(Scanner scan, Customer c) {
    	String resp = new String();
    	Database db = new Database();
    	
    	List<Account> accounts = db.getAccounts(c);
    	boolean sameName = false;
    	do {
    		pageHeader("CREATE BANK ACCOUNT PAGE");
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
    
    static boolean accountsMenu(Scanner scan, Customer c) {
    	Database db = new Database();
    	
    	// save user inputs for validation
    	String key = "";
		double amount = 0;
		String name1 = "";
		String name2 = "";
		boolean validInput = false;
    	
    	List<Account> accounts = db.getAccounts(c);
    	Account currA = null, destA = null;
    	
    	pageHeader("ACCOUNT LEDGER");
    	
    	if (accounts == null) {
    		System.out.println("You currently have no accoounts. Return to previous" +
    							"\npage to create some.");
    		toConsole("\nOPTIONS: ");
    		toConsole("BACK to previous page");
	    	toConsole("Choose action: ");
			key = scan.next().toUpperCase();
			
			switch (toAction(key)) {
			case BACK:
				return false;
			default:
				break;
			}
    		
    	} else {
    		// display customer's accounts
    		for (int i=0; i<accounts.size(); i++) {
        		currA = accounts.get(i);
        		toConsole(i+1 + ". " + currA.getName() + "......." + currA.getBalance());
        	}
    		
    		// allow customer to perform transactions
        	do {
    			toConsole("\nOPTIONS: ");
    	    	toConsole("1. DEPOSIT [amount] [dest. account name]");
    	    	toConsole("2. WITHDRAW [amount] [source account name]");
    	    	toConsole("3. TRANSFER [amount] [source account name]"
    	    								 + "[dest. account name]");
    	    	toConsole("BACK to previous page");
    	    	toConsole("Choose action: ");
    			key = scan.next().toUpperCase();
    			
    			try {
    				// break down user input to variables
    				switch(toAction(key)) {
    				case DEPOSIT:
    				case WITHDRAW:
    					amount = scan.nextDouble();
    					name1 = scan.next();
    					name2 = "";
    					break;
    				case TRANSFER:
    					amount = scan.nextDouble();
    					name1 = scan.next();
    					name2 = scan.next();
    					break;
    				case BACK:
    					return false;
    				default:
    					System.out.println("INVALID KEYWORD ENTERED!");
    				}

    				// check if account names exist
    				for(Account a : accounts) {
    					if (name1.contentEquals(a.getName())) {
    						currA = a;
    						validInput = true;
    						break;
    					}
    				}
    				
    				if (!name2.isEmpty()) {
    					for(Account a : accounts) {
    						if (name2.contentEquals(a.getName())) {
    							destA = a;
    							validInput = true;
    							break;
    						} 
    					}
    				}
    			} catch (InputMismatchException e) {
    				e.getMessage();
    				validInput = false;
    				System.out.println("INVALID INPUT IN COMMAND!\n");
    			} 	
    		} while(!validInput);
    		
        	// format amount to money format (i.e. 1.00)
        	String amountStr = String.format("%.2f", amount);
        	amount = Double.parseDouble(amountStr);
        	
        	switch (key) {
    		case "DEPOSIT":
    			c.depositMoney(currA, amount);
    			break;
    		case "WITHDRAW":
    			c.withdrawMoney(currA, amount);
    			break;
    		case "TRANSFER":
    			c.transferMoney(currA, destA, amount);
    		default:
    			break;
    		}
    	}

    	return true;
    }
    
    static User loginVerification(List<String> resps) {
		DAOUtil dao = new DAOUtil();
    	     EmployeeDAO eDAO = dao.getEmployeeDAOImpl();
    	
    	     List<User> users = eDAO.getAllUsers();
    	     User user = null;
    	
    	     boolean foundUser = false;
    	
    	     for (User u : users) {
		     // found user name in users set
		     if (u.getUsername().contentEquals(resps.get(0))) {
			     // compare user password w/ entered password
			     if (u.getPassword().contentEquals(resps.get(1))) {
					user = u;
				     foundUser = true;
			     } else {
				     System.out.println("INCORRECT PASSWORD!");
			     }
		    }
	    }
    	
    	    if(!foundUser) {
    	        System.out.println("USERNAME NOT FOUND!");
		}
    	    return user;
    }

    static Action toAction(String string) {
    	Action action = Action.NOTHING;
 
    	try {
    		action = Action.valueOf(string.toUpperCase());
    	} catch (IllegalArgumentException e) {
    		System.out.println("INVALID KEYWORD ENTERED!");
    	}
    	
    	return action;
    }
}