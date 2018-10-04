package com.revature;

import java.util.HashSet;
import java.util.Scanner;

import com.revature.models.*;

enum Action {
	REGISTER, LOGIN, CREATE, ACCESS, DEPOSIT, WITHDRAW, TRANSFER,BACK, LOGOUT, EXIT, NOTHING;
}

public class BankingApp {
    public static void main(String[] args) {
    	String key = new String();
    	Scanner s = new Scanner(System.in);

    	Customer c = null;
    	HashSet<User> users = new HashSet<>();
    	
    	boolean isLoggedIn = false;
    	boolean accessingAccounts = false;
    	
    	while(true) {
    		if (!isLoggedIn) {
    			key = Menus.startMenu(s);

    			// exit application
        		if (toAction(key) == Action.EXIT) {
        			break;
        		}
    			
        		// welcome pages
    			switch (toAction(key)) {
    			case REGISTER:
    				c = Menus.registerMenu(s);
    				 users.add(c);
    				 break;
    			case LOGIN:
    				isLoggedIn = Menus.loginMenu(s, users);
    				break;
    			default:
    				break;
    			}	
    		} else 
    		if (!accessingAccounts){
    			key = Menus.userOptionsMenu(s);
        		
        		// user bank account pages
    			switch (toAction(key)) {
    			case CREATE:
    				
    				break;
    			case ACCESS:
    				accessingAccounts = true;
    				break;
    			case LOGOUT:
    				c = null;
    				isLoggedIn = false;
    				break;
    			default:
    				break;
    			}
    		} else {
    			accessingAccounts = Menus.accountsMenu(s, c);
    		}
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