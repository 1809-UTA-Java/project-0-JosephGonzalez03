package com.revature.dao;

import java.util.List;

import com.revature.models.Account;

public interface AccountDAO {
	public List<Account> getAllAccounts();
	public List<Account> getAccountsByName(Account account);
	public Account getAccountByNumber(Account account);
	
	public boolean addAccount(Account account);
	public boolean removeAccount(Account account);
}