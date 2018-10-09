package com.revature;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.revature.dao.EmployeeDAO;
import com.revature.duo.CustomerDUO;
import com.revature.models.*;
import com.revature.util.*;

public class Menus {
	
    static void toConsole(String message) {
        System.out.println(message);
    }
    
    static void pageHeader(String pageName) {
    	  toConsole("\n\n\n=====================================");
          toConsole(pageName);
          toConsole("=====================================");
    }
    
    static void tableHeader(String tableName) {
  	  toConsole("\n\n\n");
        toConsole(tableName);
        toConsole("---------------------------------------");
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
        String username = "", pwrd = "", first = "", last = "", email = "";
        int phone;
        String confirmedPassword = new String();

        Menus.pageHeader("USER ACCOUNT REGISTRATION");

        toConsole("Please enter user account information.");
        toConsole("Username: ");
        username = scan.nextLine();
        
        // ask for password until password & confirmed password match
        do {
            toConsole("Password: ");
            pwrd = scan.nextLine();

            toConsole("Confirm password: ");
            confirmedPassword = scan.nextLine();
        } while (!pwrd.contentEquals(confirmedPassword));
        
        Menus.pageHeader("ADDITIONAL USER INFORMATION");
    	toConsole("First Name: ");
    	first = scan.nextLine();
    	toConsole("Last Name: ");
    	last = scan.nextLine();
    	toConsole("Phone Number: ");
    	phone = scan.nextInt();
    	toConsole("E-MAIL: ");
    	email = scan.nextLine();
    	
    	toConsole("\n\n\n\n\n\n");
        return new Customer(username, pwrd, first, last, phone, email);
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

    static User loginVerification(List<String> resps) {
    	EmployeeDAO eDAO = DAOUtil.getEmployeeDAO();
    	
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

    static void displayAccountContents(Account a) {
    	if (a.isApproved()) {
    		System.out.format("%15s %15d %15.2f \n\n", a.getName(), a.getNumber(), a.getBalance());
    	} else {
    		System.out.format("%15s %15d %15.2f %15s \n\n", a.getName(), a.getNumber(), a.getBalance(), "[UNAPPROVED]");
    	}
    }
    
    static void displayCustomerProfile(Customer c) {
    	System.out.format("%20s %20s %20s %10d %20s \n\n", c.getFirstName(), c.getLastName(), c.getUsername(), c.getPhone(), c.getEmail());
    }
    
    static void displayEmployeeProfile(Employee e) {
    	if (e.isAdmin()) {
    		System.out.format("%20s %20d %20s %15s \n\n", e.getFirstName(), e.getLastName(), e.getUsername(), "[ADMIN]");
    	} else {
    		System.out.format("%20s %20s %20s \n\n", e.getFirstName(), e.getLastName(), e.getUsername());
    	}
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