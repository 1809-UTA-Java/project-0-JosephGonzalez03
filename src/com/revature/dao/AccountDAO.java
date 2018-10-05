package com.revature.dao;

import java.util.List;

import com.revature.models.Account;

public interface AccountDAO {
	public List<Account> getAllAccounts();
	public List<Account> getAccountsByName(String accountName);
	public List<Account> getAccountsByUserName(String userName);
	public List<Account> getAccountsByNumber(Long accountNumber);
	
	public boolean addAccount(Account account);
	public boolean removeAccount(Account account);
}
