package com.revature.dao;

import java.util.List;

import com.revature.models.Account;

public interface AccountDAO {
	public List<Account> getAllAccounts();
	public List<Account> getUnapprovedAccounts();
	public List<Account> getAccountsByUsername(String userName);
	public Account getAccountByNumber(int accountNumber);
	public Account getAccountByName(String accountName);
	
	public boolean addAccount(Account account);
	public boolean removeAccount(Account account);
	public boolean updateBalance(int accountNumber, double balance);
}
