package com.revature;

import java.util.Scanner;

public abstract class Page {
	static void toConsole(String message) {
        System.out.println(message);
    }
	
	abstract String optionsMenu(Scanner scan);
	abstract boolean customerProfile(Scanner scan, String customer_username);
}
