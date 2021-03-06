package com.revature.dao;

import java.util.List;

import com.revature.models.Account;
import com.revature.models.Customer;

public interface CustomerDAO {
	public List<Customer> getAllCustomers();
	public List<Customer> getCustomersByAccountNumber(Account account);
	public Customer getCustomerByUsername(String username);
	
	public boolean addCustomer(Customer customer);
	public boolean deleteCustomer(Customer customer);
}
