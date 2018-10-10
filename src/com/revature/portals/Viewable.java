package com.revature.portals;

import java.util.Scanner;

public interface Viewable {
	
	String optionsMenu(Scanner scan);
	void customerProfileMenu();
	boolean customerProfile(Scanner scan, String customer_username);
	
	default void toConsole(String message) {
        System.out.println(message);
    }
}
