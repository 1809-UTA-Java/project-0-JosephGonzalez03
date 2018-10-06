package com.revature;

public class Portals {

     public static boolean customerPortal(Scanner s) {
           boolean accessingAccounts = false;  
		   boolean isLoggedIn = true;
		   
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
    		
    		return isLoggedIn;
     }
     
     
     private Action toAction(String string) {
    	Action action = Action.NOTHING;
 
    	try {
    		action = Action.valueOf(string.toUpperCase());
    	} catch (IllegalArgumentException e) {
    		System.out.println("INVALID KEYWORD ENTERED!");
    	}
    	
    	return action;
    }
}