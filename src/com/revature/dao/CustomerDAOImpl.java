package com.revature.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Account;
import com.revature.models.Customer;
import com.revature.util.DAOUtil;

public class CustomerDAOImpl implements CustomerDAO {

	Connection conn = null;
	PreparedStatement ps = null;
	
	@Override
	public List<Customer> getAllCustomers() {
		Customer c = null;
		List<Customer> customers = new ArrayList<>();
		
		try {
			conn = DAOUtil.getConnection();
			String sql = "SELECT * FROM CUSTOMERS";
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				String username = rs.getString("username");
				String pwrd = rs.getString("pwrd");
				String first = rs.getString("firstName");
				String last = rs.getString("lastName");
				int phone = rs.getInt("phone");
				String email = rs.getString("email");
				
				c = new Customer(username, pwrd, first, last, phone, email);
				customers.add(c);
			}
			
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.getMessage();
		} catch (IOException e) {
			e.getMessage();
		}
		
		return customers;
	}

	/******************************************************************/
	
	@Override
	public List<Customer> getCustomersByAccountNumber(Account account) {
		Customer c = null;
		List<Customer> customers = new ArrayList<>();
		
		try {
			conn = DAOUtil.getConnection();
			String sql = "SELECT * FROM CUSTOMERS WHERE username in"
					+ "SELECT username FROM ACCOUNTS WHERE number LIKE ?";
			ps = conn.prepareStatement(sql);
			ps.setLong(1, account.getNumber());
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				String username = rs.getString("username");
				String pwrd = rs.getString("pwrd");
				String first = rs.getString("firstName");
				String last = rs.getString("lastName");
				int phone = rs.getInt("phone");
				String email = rs.getString("email");
				
				c = new Customer(username, pwrd, first, last, phone, email);
				customers.add(c);
			}
			
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.getMessage();
		} catch (IOException e) {
			e.getMessage();
		}
		
		return customers;
	}

	/******************************************************************/
	
	@Override
	public List<Customer> getCustomersByFirstName(Customer customer) {
		Customer c = null;
		List<Customer> customers = new ArrayList<>();
		
		try {
			conn = DAOUtil.getConnection();
			String sql = "SELECT * FROM CUSTOMERS WHERE firstName LIKE ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, customer.getFirstName());
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				String username = rs.getString("username");
				String pwrd = rs.getString("pwrd");
				String first = rs.getString("firstName");
				String last = rs.getString("lastName");
				int phone = rs.getInt("phone");
				String email = rs.getString("email");
				
				c = new Customer(username, pwrd, first, last, phone, email);
				customers.add(c);
			}
			
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.getMessage();
		} catch (IOException e) {
			e.getMessage();
		}
		
		return customers;
	
	}

	/******************************************************************/
	
	@Override
	public List<Customer> getCustomersByLastName(Customer customer) {
		Customer c = null;
		List<Customer> customers = new ArrayList<>();
		
		try {
			conn = DAOUtil.getConnection();
			String sql = "SELECT * FROM CUSTOMERS WHERE firstName LIKE ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, customer.getLastName());
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				String username = rs.getString("username");
				String pwrd = rs.getString("pwrd");
				String first = rs.getString("firstName");
				String last = rs.getString("lastName");
				int phone = rs.getInt("phone");
				String email = rs.getString("email");
				
				c = new Customer(username, pwrd, first, last, phone, email);
				customers.add(c);
			}
			
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.getMessage();
		} catch (IOException e) {
			e.getMessage();
		}
		
		return customers;
	}

	/******************************************************************/
	
	@Override
	public boolean addCustomer(Customer customer) {
		try {
			conn = DAOUtil.getConnection();
			String sql = "INSERT INTO CUSTOMERS VALUES(?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, customer.getUsername());
			ps.setString(2, customer.getPassword());
			ps.setString(3, customer.getFirstName());
			ps.setString(4, customer.getLastName());
			ps.setInt(5, customer.getPhone());
			ps.setString(6, customer.getEmail());
			

			if (ps.executeUpdate() != 0) {
				ps.close();
				return true;
			} else {
				ps.close();
				return false;
			} 
			
		} catch (SQLException e) {
			e.getMessage();
			return false;
		} catch (IOException e) {
			e.getMessage();
			return false;
		}
	}

	/******************************************************************/
	
	@Override
	public boolean deleteCustomer(Customer customer) {
		Customer c = null;
		List<Customer> customers = new ArrayList<>();
		
		try {
			conn = DAOUtil.getConnection();
			String sql = "DELETE FROM CUSTOMERS WHERE username = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, customer.getUsername());

			if (ps.executeUpdate() != 0) {
				ps.close();
				return true;
			} else {
				ps.close();
				return false;
			} 
			
		} catch (SQLException e) {
			e.getMessage();
			return false;
		} catch (IOException e) {
			e.getMessage();
			return false;
		}	
	}

}
