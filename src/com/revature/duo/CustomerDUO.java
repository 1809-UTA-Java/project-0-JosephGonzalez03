package com.revature.duo;

import com.revature.dao.DAOUtil;

public class CustomerDAO implements Transcationable {
    
    @Override
	public boolean depositMoney(Account account, double amount) {
		DAOUtil dao = new DAOUtil();
		AccountDAO aDAO = dao.getAccountDAO();
		
		double currBalance = account.getBalance();
		
		if (amount < 0) {
		  System.out.println("AMOUNT CANNOT BE NEGATIVE!");
	      return false;
	  }
	        
	  account.setBalance(currBalance + amount);
	  
	  // update account in SQL database
	  if (aDAO.removeAccount(account)) {		
		    return aDAO.addAccount(account);
		}
	  return false;
	}
	
	@Override
	public boolean withdrawMoney(Account account, double amount) {
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
        
	  // update account in SQL database
	  if (aDAO.removeAccount(account)) {		
		    return aDAO.addAccount(account);
		}
	  return false;
	}

	@Override
	public boolean transferMoney(Account sourceAccount, Account destAccount, double amount) {
		if(withdrawMoney(sourceAccount, amount)) {
			return depositMoney(destAccount, amount);
		}
    	return false;
	}
}