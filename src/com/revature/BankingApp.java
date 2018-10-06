package com.revature;

import java.util.HashSet;
import java.util.Scanner;

import com.revature.models.*;

enum Action {
	REGISTER, LOGIN, CREATE, ACCESS, DEPOSIT, WITHDRAW, TRANSFER,BACK, LOGOUT, EXIT, NOTHING;
}

enum User {
	ADMIN, EMPLOYEE, CUSTOMER;
}

public class BankingApp {
    public static void main(String[] args) {
    	String key = new String();
    	Scanner s = new Scanner(System.in);

    	User user = null;
    	
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
    				 users.add(user);
    				 break;
    			case LOGIN:
    				isLoggedIn = Menus.loginMenu(s, users);
    				break;
    			case EXIT: 
    			    terminate = true;
    			default:
    				break;
    			}	
    		} else {
    		    switch(////) {
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