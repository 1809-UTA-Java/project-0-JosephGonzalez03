package com.revature;

import java.util.ArrayList;
import java.util.HashSet;
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
        toConsole("Welcome to your Joseph Banking\n");
        toConsole("1. REGISTER for a user account");
        toConsole("2. LOGIN to an existing one");
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
    	
        return new Customer(resps.get(0),resps.get(1),resps.get(2),resps.get(3),resps.get(4),resps.get(5));
    }

    static boolean loginMenu(HashSet<User> users) {
    	Scanner scan = new Scanner(System.in);
    	List<String> responses = new ArrayList<>();

    	do {
    		pageHeader("LOGIN PAGE");
    		toConsole("Please username and password.");
    		toConsole("Username: ");
    		responses.add(scan.nextLine());
    		toConsole("Password: ");
    		responses.add(scan.nextLine());;    		
    	} while(!loginVerification(users, responses));

        scan.close();
        return true;
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
    
    static Account createAccountMenu(Customer c) {
    	Scanner scan = new Scanner(System.in);
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
    	
    	scan.close();
    	return new Account(resp);
    }
    
    static void accountsMenu(Customer c) {
    	Database db = new Database();
    	List<Account> accounts = db.getAccounts(c);
    	Account curr;
    	
    	for (int i=0; i<accounts.size(); i++) {
    		curr = accounts.get(i);
    		toConsole(i+1 + ". " + curr.getName() + "......." + curr.getBalance());
    	}
    }
    
    static boolean loginVerification(HashSet<User> users, List<String> resps) {
    	for (User u : users) {
			// found user name in users set
			if (u.getUsername().contentEquals(resps.get(0))) {
				// compare user password w/ entered password
				if (u.getPassword().contentEquals(resps.get(1))) {
					return true;
				} else {
					System.out.println("INCORRECT PASSWORD!");
				}
			} else {
				System.out.println("USERNAME NOT FOUND!");
			}
		}
    	return false;
    }
}