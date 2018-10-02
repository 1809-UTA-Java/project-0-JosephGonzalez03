package com.revature;

import java.util.Scanner;

public class Menus {

    static void toConsole(String message) {
        System.out.println(message);
    }
    
    static void pageHeader(String pageName) {
    	  toConsole("\n\n\n=====================================");
          toConsole(pageName);
          toConsole("=====================================");
    }

    static String startMenu(Scanner scan) {
        toConsole("Welcome to your Joseph Banking\n");
        toConsole("1. REGISTER for a user account or \n" +
                  "2. LOGIN to an existing one?");
        toConsole("Choose action: ");
        
        return scan.next();
    }

    static String[] registerMenu(Scanner scan) {
        String[] response = new String[2];
        String confirmedPassword = new String();

        pageHeader("USER ACCOUNT REGISTRATION");

        toConsole("Please enter user account information.");
        toConsole("Username: ");
        response[0] = scan.next();
        
        // ask for password until password & confirmed password match
        do {
            toConsole("Password: ");
            response[1] = scan.next();

            toConsole("Confirm password: ");
            confirmedPassword = scan.next();
        } while (!response[1].equals(confirmedPassword));

        return response;
    }

    static String[] loginMenu(Scanner scan) {
        String[] response = new String[2];

        pageHeader("LOGIN PAGE");
        
        toConsole("Please username and password.");
        toConsole("Username: ");
        response[0] = scan.next();
        toConsole("Password: ");
        response[1] = scan.next();

        return response;
    }

    static void accountMenu(Scanner scan) {
    	pageHeader("ACCOUNT MANAGEMENT PAGE");
    	
        toConsole("What would you like to do?");
        toConsole("1. CREATE a new account");
        toConsole("2. ACCESS...");
        
    }
}