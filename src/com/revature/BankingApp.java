package com.revature;

import java.util.Scanner;

import com.revature.dao.*;
import com.revature.duo.*;
import com.revature.models.*;
import com.revature.util.DAOUtil;
import com.revature.util.DUOUtil;

enum Action {
	REGISTER, LOGIN, CREATE, ACCESS, DEPOSIT, WITHDRAW, TRANSFER, 
	SHOW, GET, APPROVE, DENY, CANCEL, BACK, LOGOUT, EXIT, NOTHING;
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

    	// users
    	User user = null;
    	Employee employee = null;
    	Customer customer = null;
    	
    	BankUser bankUser = null;
    	
    	boolean terminate = false;
    	boolean isLoggedIn = false;	
    	
    	while(!terminate) {
    		// login procedure 
    		if (!isLoggedIn) {
    			key = Menus.startMenu(s);
    			
        		// welcome pages
    			switch (Menus.toAction(key)) {
    			case REGISTER:
    				 user = Menus.registerMenu(s);
    				 break;
    			case LOGIN:
    				username = Menus.loginMenu(s);
				
    				// get full user profile from sql database
    				user = cDAO.getCustomerByUsername(username);
				
				// determine if user is customer or employee
				if (user instanceof Customer) {
				    customer = (Customer) user;
				    employee = null;
				    bankUser = BankUser.CUSTOMER;
				} else {
				    customer = null;
				    employee = (Employee) user;
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
    		    	Portals.adminPortal(employee, s);
    		        break;
    		     case EMPLOYEE: 
    		    	 Portals.employeePortal(employee, s);
    		        break;
			case CUSTOMER: 
    		    	Portals.customerPortal(customer, s);
    		        break;
			default:
				break;
    		    }
    		}
    		
    		
    		/////
    	}
	}
}