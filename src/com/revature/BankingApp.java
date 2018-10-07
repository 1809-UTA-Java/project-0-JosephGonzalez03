package com.revature;

import java.util.HashSet;
import java.util.Scanner;

import com.revature.models.*;

enum Action {
	REGISTER, LOGIN, CREATE, ACCESS, DEPOSIT, WITHDRAW, TRANSFER,BACK, LOGOUT, EXIT, NOTHING;
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
     DAOUtil dao = new DAOUtil();
	CustomerDAO cDAO = dao.getCustomerDAO();

     // duo objects
    	DUOUtil duo =	new DUOUtil();
    	EmployeeDUO eDUO = duo.getEmployeeDUO();
    	CustomerDUO cDUO = duo.getCustomerDUO();
    	
    	// users
    	User user = null;
    	Employee employee = null;
    	Customer customer = null;
    	
    	BankUser bankUser;
    	
    	boolean terminate = false;
    	boolean isLoggedIn = false;	
    	
    	while(!terminate) {
    		// login proceedure 
    		if (!isLoggedIn) {
    			key = Menus.startMenu(s);
    			
        		// welcome pages
    			switch (toAction(key)) {
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
				
				isLoggedIn = true
    				break;
    			case EXIT: 
    			    terminate = true;
    			default:
    				break;
    			}	
    		} else {
    		    switch(bankUser) {
    		    case ADMIN: 
    		    
    		        break;
    		     case EMPLOYEE: 
    		    
    		        break;
			case CUSTOMER: 
    		    
    		        break;
			default:
				break;
    		    }
    		}
    		
    		
    		/////
    	}
	}
}