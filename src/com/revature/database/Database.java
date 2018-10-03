package com.revature.database;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.revature.models.*;

public class Database {
	static Map<String, List<Account>> accountsTable = new Hashtable<String, List<Account>>();
	
	/**
	 * Adds new account to accountTable.
	 * Prints warning if duplicate account is passed in.
	 * 
	 * @param username
	 * @param account
	 */
	public boolean addAccount(User u, Account a) {
		// get all accounts associated w/ user 
		List<Account> loggedAccounts = accountsTable.get(u.getUsername());
		List<Account> tempLog = loggedAccounts;
		
		tempLog.add(a);
		
		// convert to hash to delete duplicate accounts
		HashSet<Account> hashedTempLog = new HashSet<>(tempLog);
		
		// add new account to log if no duplicates in hashedTempLog
		if (tempLog.size() == hashedTempLog.size()) {
			// remove key & value from map
			accountsTable.remove(u.getUsername());
			
			// add new account to retrieved accounts
			loggedAccounts.add(a);
			
			// add map & key back to map w/ added account
			accountsTable.put(u.getUsername(), loggedAccounts);
		} else {
			System.out.println("WARNING: That account already exist for " + 
					u.getUsername() + "! Entry was not added!");
			return false;
		}
		
		return true;
	}
	
	public boolean removeAccount(User u, Account a) {
		// get all accounts associated w/ user 
		List<Account> loggedAccounts = accountsTable.get(u.getUsername());
				
		// remove key & value from map
		accountsTable.remove(u.getUsername());
				
		// remove account from retrieved accounts
		for(Account a1 : loggedAccounts) {
			if (a1.getAccountNumber().contains(a1.getAccountNumber())) {
				loggedAccounts.remove(a1);
				
				// add map & key back to map w/ removed accounts
				accountsTable.put(u.getUsername(), loggedAccounts);
				return true;
			}
		}
		
		// account not removed if this executes
		return false;				
	}
	
	public boolean updateAccount(User u, Account a) {
		if (removeAccount(u, a)) {
			// might return false here
			return addAccount(u, a);			
		} 
		
		// account not updated if this executes
		return false;
	
	}
	
	public List<Account> getAccounts(User u) {
		return accountsTable.get(u.getUsername());
	}
	
	/**
	 * Adds new user to account table.
	 * Prints warning if duplicate user name is passed in.
	 * 
	 * @param username
	 * @return
	 */
	public boolean addUser(User u) {
		Set<String> users = accountsTable.keySet();
		List<Account> accounts = new ArrayList<>();
		
		if (users.contains(u.getUsername())) {
			System.out.println("WARNING: " + u.getUsername() + " already exists! " +
					"Entry was not added!");
			return false;
		} else {
			accountsTable.put(u.getUsername(), accounts);			
		}
		
		return true;
	}
	
	public boolean removeUser(User u) {
		List<Account> loggedAccounts = accountsTable.get(u.getUsername());
		int initSize = loggedAccounts.size();
		
		accountsTable.remove(u.getUsername());
		
		if (initSize == accountsTable.size()) {
			return true;
		}
		
		return false;
	}
}
