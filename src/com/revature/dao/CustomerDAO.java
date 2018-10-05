package com.revature.dao;

import java.util.List;

import com.revature.models.Account;
import com.revature.models.Customer;

public interface CustomerDAO {
	public List<Customer> getAllCustomers();
	public List<Customer> getCustomersByAccountNumber(Account account);
	public List<Customer> getCustomersByFirstName(Customer customer);
	public List<Customer> getCustomersByLastName(Customer customer);
	
	public boolean addCustomer(Customer customer);
	public boolean deleteCustomer(Customer customer);
}
