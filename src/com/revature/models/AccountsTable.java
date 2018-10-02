package com.revature.models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AccountsTable {

	Map<String, List<Account>> accountsTable = new Hashtable<String, List<Account>>();
	
	/**
	 * Adds new account to accountTable.
	 * Prints warning if duplicate account is passed in.
	 * 
	 * @param username
	 * @param account
	 */
	public boolean addAccount(String username, Account account) {
		// get all accounts associated w/ user 
		List<Account> loggedAccounts = accountsTable.get(username);
		List<Account> tempLog = loggedAccounts;
		
		tempLog.add(account);
		
		// convert to hash to delete duplicate accounts
		HashSet<Account> hashedTempLog = new HashSet<>(tempLog);
		
		// add new account to log if no duplicates in hashedTempLog
		if (tempLog.size() == hashedTempLog.size()) {
			// remove key & value from map
			accountsTable.remove(username);
			
			// add new account to retrieved accounts
			loggedAccounts.add(account);
			
			// add map & key back to map w/ added account
			accountsTable.put(username, loggedAccounts);
		} else {
			System.out.println("WARNING: That account already exist for " + 
					username + "! Entry was not added!");
			return false;
		}
		
		return true;
	}
	
	public boolean removeAccount(String username, String accountNumber) {
		// get all accounts associated w/ user 
		List<Account> loggedAccounts = accountsTable.get(username);
				
		// remove key & value from map
		accountsTable.remove(username);
				
		// remove account from retrieved accounts
		for(Account a : loggedAccounts) {
			if (a.getAccountNumber().contains(accountNumber)) {
				loggedAccounts.remove(a);
				
				// add map & key back to map w/ removed accounts
				accountsTable.put(username, loggedAccounts);
				return true;
			}
		}
		
		// account not removed if this executes
		return false;				
	}
	
	public boolean updateAccount(String username, Account account) {
		if (removeAccount(username, account.getAccountNumber())) {
			// might return false here
			return addAccount(username, account);			
		} 
		
		// account not updated if this executes
		return false;
	
	}
	
	/**
	 * Adds new user to account table.
	 * Prints warning if duplicate user name is passed in.
	 * 
	 * @param username
	 * @return
	 */
	public boolean addUser(String username) {
		Set<String> users = accountsTable.keySet();
		List<Account> accounts = new ArrayList<>();
		
		if (users.contains(username)) {
			System.out.println("WARNING: " + username + " already exists! " +
					"Entry was not added!");
			return false;
		} else {
			accountsTable.put(username, accounts);			
		}
		
		return true;
	}
	
	public boolean removeUser(String username) {
		List<Account> loggedAccounts = accountsTable.get(username);
		int initSize = loggedAccounts.size();
		
		accountsTable.remove(username);
		
		if (initSize == accountsTable.size()) {
			return true;
		}
		
		return false;
	}
}
