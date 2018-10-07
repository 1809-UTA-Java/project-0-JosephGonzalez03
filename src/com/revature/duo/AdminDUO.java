package com.revature.duo;

import com.revature.dao.DAOUtil;

public class AdminDAO implements Transcationable {
    
    public boolean cancelAccount(Account account) {
		DAOUtil dao = new DAOUtil();
		AccountDAO aDAO = dao.getAccountDAO();
		
		return aDAO.removeAccount();
	}
    
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
	  
	  return aDAO.updateBalance(account);
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
        
		return aDAO.updateBalance(account);
	}

	@Override
	public boolean transferMoney(Account sourceAccount, Account destAccount, double amount) {
		if(withdrawMoney(sourceAccount, amount)) {
			return depositMoney(destAccount, amount);
		}
    	return false;
	}
}