package com.revature;

import java.util.Scanner;

public class Menu {

    void toConsole(String message) {
        System.out.println(message);
    }

    int startMenu(Scanner scan) {
        toConsole("Welcome to your Joseph Banking\n");
        toConsole("(1) Would you like to register for a user account or \n" 
                   + "(2) login to an existing one?");
        toConsole("Please enter action number: ");
        
        return scan.nextInt();
    }

    String[] registerMenu(Scanner scan) {
        String[] response = new String[2];
        String confirmedPassword = new String();

        toConsole("-------------------------------------");
        toConsole("----- USER ACCOUNT REGISTRATION -----");
        toConsole("-------------------------------------\n\n\n");


        toConsole("Please enter user account information.");
        toConsole("Username: ");
        response[0] = scan.nextLine();
        
        // ask for password until password & confirmed password match
        do {
            toConsole("Password: ");
            response[1] = scan.nextLine();

            toConsole("Confirm password: ");
            confirmedPassword = scan.nextLine();
        } while (!response[1].equals(confirmedPassword));

        return response;
    }

    String[] loginMenu(Scanner scan) {
        String[] response = new String[2];

        toConsole("-----------------------------------");
        toConsole("----------- LOGIN PAGE ------------");
        toConsole("-----------------------------------\n\n\n");

        toConsole("Please username and password.");
        toConsole("Username: ");
        response[0] = scan.nextLine();
        toConsole("Password: ");
        response[1] = scan.nextLine();

        return response;
    }

    void accountMenu(Scanner scan) {
        toConsole("-----------------------------------");
        toConsole("----- ACCOUNT MANAGEMENT PAGE -----");
        toConsole("-----------------------------------\n\n\n");

        toConsole("What would you like to do?");
        toConsole("(1) Create a new account");
        
    }
}