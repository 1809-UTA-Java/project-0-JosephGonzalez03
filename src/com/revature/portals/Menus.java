package com.revature.portals;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.revature.dao.EmployeeDAO;
import com.revature.models.*;
import com.revature.util.*;

enum Action {
	CREATE, ACCESS, DEPOSIT, WITHDRAW, TRANSFER, 
	SHOW, GET, APPROVE, DENY, CANCEL, BACK, LOGOUT, NOTHING;
}

public class Menus {
	public static void toConsole(String message) {
        System.out.println(message);
    }
    
	public static void pageHeader(String pageName) {
    	  toConsole("\n\n\n=====================================");
          toConsole(pageName);
          toConsole("=====================================");
    }
    
	public static void tableHeader(String tableName) {
  	  toConsole("\n");
        toConsole(tableName);
        toConsole("-------------------------------------------------------");
	}
    
	public static String startMenu(Scanner scan) {
        toConsole("\n\nWelcome to your Personal Banking Application!\n");
        toConsole("1. REGISTER for a user account");
        toConsole("2. LOGIN fromTo an existing one");
        toConsole("3. EXIT banking app");
        toConsole("Choose action: ");
        
        return scan.nextLine();
    }

	public static void registerMenu(Scanner scan) {
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
    	DAOUtil.getCustomerDAO().addCustomer(new Customer(username, pwrd, first, last, phone, email));
    }

	public static String loginMenu(Scanner scan) {
		User user = null;
    	List<String> responses = new ArrayList<>();

    	boolean isVerified = false;
    	int failedCounter = 0;

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
    				
    		failedCounter++;
    	} while(!isVerified && failedCounter < 3);

        return user.getUsername();
    }

	public static User loginVerification(List<String> resps) {
    	EmployeeDAO eDAO = DAOUtil.getEmployeeDAO();
    	
    	List<User> users = eDAO.getAllUsers();
    	User user = null;
    	
    	boolean foundUser = false;
    	boolean wrongPwrd = false;
    	
    	for (User u : users) {
    		// found user name in users set
		    if (u.getUsername().contentEquals(resps.get(0))) {
		    	// compare user password w/ entered password
			    if (u.getPassword().contentEquals(resps.get(1))) {
			    	user = u;
				    foundUser = true;
			    } else {
			    	wrongPwrd = true;
			    	System.out.println("INCORRECT PASSWORD!");
			    }
		    }
	    }
    	
    	if(!foundUser && !wrongPwrd) {
    		System.out.println("USERNAME NOT FOUND!");
		}
    	return user;
    }

	public static void accountTableHeader() {
		System.out.format("%15s %15s %15s \n", "", "ACCOUNTS", "");
		toConsole("-----------------------------------------------------------------");
		System.out.format("%15s %15s %15s \n", "Name", "Number", "Balance");
		toConsole("-----------------------------------------------------------------");
	}
	
	public static void customerTableHeader() {
		System.out.format("%19s %19s %19s \n", "", "CUSTOMERS", "");
		toConsole("------------------------------------------------------------------");
		System.out.format("%12s %12s %12s %12s %12s \n", "First Name", "Last Name", "Username", "Phone #", "E-mail");
		toConsole("------------------------------------------------------------------");
	}

	public static void employeeTableHeader() {
		System.out.format("%12s %12s %12s \n", "", "EMPLOYEES", "");
		toConsole("-------------------------------------------------------");
		System.out.format("%12s %12s %12s \n", "First Name", "Last Name", "Username");
		toConsole("-------------------------------------------------------");
	}

	public static void displayAccountContents(Account a) {
    	if (a.isApproved()) {
    		System.out.format("%15s %15d %15.2f \n\n", a.getName(), a.getNumber(), a.getBalance());
    	} else {
    		System.out.format("%15s %15d %15.2f %15s \n\n", a.getName(), a.getNumber(), a.getBalance(), "[UNAPPROVED]");
    	}
    }
	
	public static void displayCustomerProfile(Customer c) {
    	System.out.format("%12s %12s %12s %10d %12s \n\n", c.getFirstName(), c.getLastName(), c.getUsername(), c.getPhone(), c.getEmail());
    }
	
	public static void displayEmployeeProfile(Employee e) {
    	if (e.isAdmin()) {
    		System.out.format("%12s %12s %12s %15s \n\n", e.getFirstName(), e.getLastName(), e.getUsername(), "[ADMIN]");
    	} else {
    		System.out.format("%12s %12s %12s \n\n", e.getFirstName(), e.getLastName(), e.getUsername());
    	}
    }
    
	public static Action toAction(String string) {
    	Action action = Action.NOTHING;
 
    	try {
    		action = Action.valueOf(string.toUpperCase());
    	} catch (IllegalArgumentException e) {
    		System.out.println("INVALID KEYWORD ENTERED!");
    	}
    	return action;
    }
}