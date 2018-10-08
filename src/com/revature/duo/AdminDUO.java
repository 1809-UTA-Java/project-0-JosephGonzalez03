package com.revature.duo;

import com.revature.dao.AccountDAO;
import com.revature.models.Account;
import com.revature.util.DAOUtil;

public class AdminDUO implements Transactionable {
    
	DAOUtil dao = new DAOUtil();
	
	public boolean cancelAccount(Account account) {
		AccountDAO aDAO = dao.getAccountDAO();
		return aDAO.removeAccount(account);
	}
    
    @Override
	public boolean depositMoney(Account account, double amount) {
		AccountDAO aDAO = dao.getAccountDAO();
		
		double currBalance = account.getBalance();
		
		if (amount < 0) {
		  System.out.println("AMOUNT CANNOT BE NEGATIVE!");
	      return false;
	  }
	        
	  account.setBalance(currBalance + amount);  
	  
	  return aDAO.updateBalance(account.getNumber(), account.getBalance());
	}
	
	@Override
	public boolean withdrawMoney(Account account, double amount) {
		AccountDAO aDAO = dao.getAccountDAO();
		
		double currBalance = account.getBalance();

		if (amount < 0) {
            System.out.println("AMOUNT CANNOT BE NEGATIVE!");
            return false;
        } else 
        if (amount > account.getBalance()) {
            System.out.println("AMOUNT CANNOT EXCEED ACCOUNT BALANCE!");
            return false;
        }
        
        account.setBalance(currBalance - amount);
        
		return aDAO.updateBalance(account.getNumber(), account.getBalance());
	}

	@Override
	public boolean transferMoney(Account sourceAccount, Account destAccount, double amount) {
		if(withdrawMoney(sourceAccount, amount)) {
			return depositMoney(destAccount, amount);
		}
    	return false;
	}
}