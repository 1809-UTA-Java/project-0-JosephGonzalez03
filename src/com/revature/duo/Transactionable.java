package com.revature.duo;

import com.revature.models.Account;

public interface Transactionable {
	boolean depositMoney(Account account, double amount);
	boolean withdrawMoney(Account account, double amount);
	boolean transferMoney(Account sourceAccount, Account destAccount, double amount);
	
}
