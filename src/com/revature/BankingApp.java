package com.revature;

import java.util.Scanner;

import com.revature.dao.*;
import com.revature.models.*;
import com.revature.portals.Menus;
import com.revature.portals.Portals;
import com.revature.util.*;

enum Action {
	REGISTER, LOGIN, LOGOUT, EXIT, NOTHING;
}

enum BankUser {
	ADMIN, EMPLOYEE, CUSTOMER;
}

public class BankingApp {
    public static void main(String[] args) {
    	String key = "";
    	String username = "";
    	Scanner s = new Scanner(System.in);

    	// dao objects
    	CustomerDAO cDAO = DAOUtil.getCustomerDAO();
    	EmployeeDAO eDAO = DAOUtil.getEmployeeDAO();
    	// users
    	User user = null;
    	Employee employee = null;
    	Customer customer = null;
    	
    	BankUser bankUser = BankUser.CUSTOMER;
    	
    	boolean terminate = false;
    	boolean isLoggedIn = false;	
    	
    	while(!terminate) {
    		// login procedure 
    		if (!isLoggedIn) {
    			key = Menus.startMenu(s);
    			
        		// welcome pages
    			switch (toAction(key)) {
    			case REGISTER:
    				 Menus.registerMenu(s);
    				 break;
    			case LOGIN:
    				username = Menus.loginMenu(s);
				
    				if (username == null) {
    					continue;
    				}
    				// get full user profile from sql database
    				user = cDAO.getCustomerByUsername(username);
				
				// determine if user is customer or employee
				if (user instanceof Customer) {
				    customer = (Customer) user;
				    employee = null;
				    bankUser = BankUser.CUSTOMER;
				} else {
				    customer = null;
				    employee = eDAO.getEmployeeByUsername(username);
				    bankUser = employee.isAdmin() ? BankUser.ADMIN : BankUser.EMPLOYEE;
				}
				
				isLoggedIn = true;
    				break;
    			case EXIT: 
    			    terminate = true;
    			default:
    				break;
    			}	
    		} else {
    		    switch(bankUser) {
    		    case ADMIN: 
    		    	isLoggedIn = Portals.adminPortal(employee, s);
    		        break;
    		     case EMPLOYEE: 
    		    	 isLoggedIn = Portals.employeePortal(employee, s);
    		        break;
    		     case CUSTOMER: 
					isLoggedIn = Portals.customerPortal(customer, s);
    		        break;
    		     default:
    		    	 break;
    		    }
    		}
    	}
    	s.close();
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