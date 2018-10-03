package com.revature;

import java.util.HashSet;
import java.util.Scanner;

import com.revature.database.Database;
import com.revature.models.*;

enum Action {
	REGISTER, LOGIN, CREATE, ACCESS, BACK, LOGOUT, EXIT;
}

public class BankingApp {
    public static void main(String[] args) {
    	String key = new String();
    	Scanner s = new Scanner(System.in);

    	Customer c = null;
    	Account a = null;
    	HashSet<User> users = new HashSet<>();
    	Database db = new Database();
    	
    	boolean isLoggedIn = false;
    	
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
    				isLoggedIn = Menus.loginMenu(users);
    				break;
    			default:
    				System.out.println("INVALID KEYWORD ENTERED!");
    				break;
    			}	
    		} else {
    			key = Menus.userOptionsMenu(s);
        		
        		// user bank account pages
    			switch (toAction(key)) {
    			case CREATE:
    				
    				break;
    			case ACCESS:
    				
    				do {
    					Menus.accountsMenu(c);
    				}
    				
    				break;
    			case LOGOUT:
    				c = null;
    				isLoggedIn = false;
    				break;
    			default:
    				System.out.println("INVALID KEYWORD ENTERED!");
    				break;
    			}
    		}
    		
    	} 
	}
    
    static Action toAction(String string) {
    	return Action.valueOf(string.toUpperCase());
    }
}