package com.revature.duo;

import java.util.List;

import com.revature.dao.*;
import com.revature.models.*;
import com.revature.util.DAOUtil;

public class CustomerDUO implements Transactionable {
	
    public boolean createAccount(Account account) {
		AccountDAO aDAO = DAOUtil.getAccountDAO();
		return aDAO.addAccount(account);
    }
    
    public List<Account> getAccounts(String customer_username) {
		AccountDAO aDAO = DAOUtil.getAccountDAO();
		
		return aDAO.getAccountsByUsername(customer_username);
	}
    
    public boolean isCustomer(User user) {
        CustomerDAO cDAO = DAOUtil.getCustomerDAO();
        
        List<Customer> customers = cDAO.getAllCustomers();
        return customers.contains(user);
    }
    
    @Override
	public boolean depositMoney(Account account, double amount) {
		AccountDAO aDAO = DAOUtil.getAccountDAO();
		
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
		AccountDAO aDAO = DAOUtil.getAccountDAO();
		
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