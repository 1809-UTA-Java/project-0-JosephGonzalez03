package com.revature.duo;

import com.revature.dao.DAOUtil;

public class EmployeeDUO implements Requestable {
    
    public boolean isEmployee(User user) {
        DAOUtil dao = new DAOUtil();
        EmployeeDAO eDAO = dao.getEmployeeDAO();
        
        List<Employee> employees = eDAO.getAllEmployees();
        return employees.contains(user);
    }
    
    @Override
	public boolean approveAccount(Account account) {
	    DAOUtil dao = new DAOUtil();
		AccountDAO aDAO = dao.getAccountDAO();
		
		if (aDAO.removeAccount(account)) {
		     account.setApproved(true));
		     
		     if (aDAO.addAccount(account)) {
		         return true;
		     }
		}
		return false;
	}

	@Override
	public boolean denyAccount(Account account) {
		DAOUtil dao = new DAOUtil();
		AccountDAO aDAO = dao.getAccountDAO();
		
		if (aDAO.removeAccount(account)) {
		    return true;
		}
		return false;
	}
	
	public List<Account> getAccounts() {
	     DAOUtil dao = new DAOUtil();
		AccountDAO aDAO = dao.getAccountDAO();
		
		return aDAO.getAllAccounts();
	}
	
	public List<Account> getAccounts(String customer_username) {
	     DAOUtil dao = new DAOUtil();
		AccountDAO aDAO = dao.getAccountDAO();
		
		return aDAO.getAccountsByUsername(customer_username);
	}
	
	public Account getAccount(Long account_number) {
		DAOUtil dao = new DAOUtil();
		AccountDAO aDAO = dao.getAccountDAO();
		
		return aDAO.getAccountsByName(account_number);
	}
	
	public Account getAccount(String account_name) {
		DAOUtil dao = new DAOUtil();
		AccountDAO aDAO = dao.getAccountDAO();
		
		return aDAO.getAccountByName(account_name);
	}
}

