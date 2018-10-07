package com.revature.duo;

import com.revature.dao.DAOUtil;

public class CustomerDAO implements Transcationable {
    
    public boolean createAccount(Account account) {
		DAOUtil dao = new DAOUtil();
		AccountDAO aDAO = dao.getAccountDAO();
		return aDAO.addAccount();
    }
    
    public List<Account> getAccounts(String customer_username) {
	     DAOUtil dao = new DAOUtil();
		AccountDAO aDAO = dao.getAccountDAO();
		
		return aDAO.getAccountsByUsername(customer_username);
	}
    
    public boolean isCustomer(User user) {
        DAOUtil dao = new DAOUtil();
        CustomerDAO cDAO = dao.getCustomerDAO();
        
        List<Customer> customers = cDAO.getAllCustomers();
        return customers.contains(user);
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