package com.revature.portals;

import java.util.Scanner;

import com.revature.models.*;

public class Portals {

	public static boolean customerPortal(Customer c, Scanner s) {
		CustomerPage cp = new CustomerPage();

		String key = "";

		boolean lookingAtAccounts = false;

		while(true) {
			if (!lookingAtAccounts) {
				key = cp.optionsMenu(s);
				
				// user bank account pages
				switch (Menus.toAction(key)) {
				case CREATE:
					cp.createAccountMenu(s, c);
					break;
				case ACCESS:
					lookingAtAccounts = true;
					break;
				case LOGOUT:
					c = null;
					return false;
				default:
					break;
				}
			} else {
				lookingAtAccounts = cp.customerProfile(s, c.getUsername());
			}
		} 
	}

	public static boolean employeePortal(Employee e, Scanner s) {
		EmployeePage ep = new EmployeePage();

		String key = "", option = "";

		boolean lookingAtCustomer = false;

		while(true) {
			if (!lookingAtCustomer) {
				String[] answ = ep.optionsMenu(s).split(" ");
				key = answ[0];
				
				// get option from command if option specified
				option = answ.length == 2 ? answ[1] : "";
				
				// user bank account pages
				switch (Menus.toAction(key)) {
				case SHOW:
					ep.showCustomersOrAccounts(option);
					break;
				case GET:
					lookingAtCustomer = true;
					break;
				case LOGOUT:
					e = null;
					return false;
				default:
					break;
				}
			} else {
				lookingAtCustomer = ep.customerProfile(s, option);
			}
		}
	}

	public static boolean adminPortal(Employee e, Scanner s) {
		AdminPage ap = new AdminPage();

		String key = "", option = "";

		boolean lookingAtCustomer = false;

		while(true) {
			if (!lookingAtCustomer) {
				String[] answ = ap.optionsMenu(s).split(" ");
				key = answ[0];
				
				// get option from command if option specified
				option = answ.length == 2 ? answ[1] : "";
				
				// user bank account pages
				switch (Menus.toAction(key)) {
				case SHOW:
					ap.showCustomersOrAccountsOrEmployees(option);
					break;
				case GET:
					lookingAtCustomer = true;
					break;
				case LOGOUT:
					e = null;
					return false;
				default:
					break;
				}
			} else {
				lookingAtCustomer = ap.customerProfile(s, option);
			}
		}
	}
}