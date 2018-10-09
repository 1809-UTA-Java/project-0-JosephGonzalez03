package com.revature.duo;

import java.util.List;

import com.revature.dao.*;
import com.revature.models.*;
import com.revature.util.DAOUtil;

public class EmployeeDUO implements Requestable {
    
    public boolean isEmployee(User user) {
        EmployeeDAO eDAO = DAOUtil.getEmployeeDAO();
        
        List<Employee> employees = eDAO.getAllEmployees();
        return employees.contains(user);
    }
    
    public boolean cancelAccount(Account account) {
		AccountDAO aDAO = DAOUtil.getAccountDAO();
		return aDAO.removeAccount(account);
	}
    
    @Override
	public boolean approveAccount(Account account) {
		AccountDAO aDAO = DAOUtil.getAccountDAO();
		
		if (aDAO.removeAccount(account)) {
		     account.setApproved(true);
		     
		     if (aDAO.addAccount(account)) {
		         return true;
		     }
		}
		return false;
	}

	@Override
	public boolean denyAccount(Account account) {
		AccountDAO aDAO = DAOUtil.getAccountDAO();
		
		if (aDAO.removeAccount(account)) {
		    return true;
		}
		return false;
	}
	
	public List<Customer> getCustomers() {
		CustomerDAO cDAO = DAOUtil.getCustomerDAO();
		return cDAO.getAllCustomers();
	}
	
	public Customer getCustomer(String customer_username) {
		CustomerDAO cDAO = DAOUtil.getCustomerDAO();
		return cDAO.getCustomerByUsername(customer_username);
	}
	
	public List<Account> getAccounts() {
		AccountDAO aDAO = DAOUtil.getAccountDAO();
		return aDAO.getAllAccounts();
	}
	
	public List<Account> getAccounts(String customer_username) {
		AccountDAO aDAO = DAOUtil.getAccountDAO();
		return aDAO.getAccountsByUsername(customer_username);
	}
	
	public Account getAccount(Long account_number) {
		AccountDAO aDAO = DAOUtil.getAccountDAO();
		return aDAO.getAccountByNumber(account_number);
	}
	
	public Account getAccount(String account_name) {
		AccountDAO aDAO = DAOUtil.getAccountDAO();
		return aDAO.getAccountByName(account_name);
	}
	
	public List<Employee> getEmployees() {
		EmployeeDAO eDAO = DAOUtil.getEmployeeDAO();
        return eDAO.getAllEmployees();
	}
}

