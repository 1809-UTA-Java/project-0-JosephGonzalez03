package com.revature.dao;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Account;
import com.revature.util.DAOUtil;

public class AccountDAOImpl implements AccountDAO {
	Connection conn = null;
	PreparedStatement ps = null;
	
	@Override
	public List<Account> getAllAccounts() {
		List<Account> accounts = new ArrayList<>();
		
		try {
			conn = DAOUtil.getConnection();
			String sql = "SELECT * FROM ACCOUNTS";
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Account a = new Account();
				
				a.setNumber(rs.getLong("accNumber"));
				a.setUsername(rs.getString("username"));
				a.setName(rs.getString("accName"));
				a.setBalance(rs.getDouble("balance"));
				a.setApproved(Boolean.parseBoolean(rs.getString("isApprove")));
				
				accounts.add(a);
			}
			
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.getMessage();
		} catch (IOException e) {
			e.getMessage();
		}
		
		return accounts;
	}

	/******************************************************************/
	
	@Override
	public List<Account> getAccountsByName(String accountName) {
		List<Account> accounts = new ArrayList<>();
		
		try {
			conn = DAOUtil.getConnection();
			String sql = "SELECT * FROM ACCOUNTS WHERE accName LIKE ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, accountName);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Account a = new Account();
				
				a.setNumber(rs.getLong("accNumber"));
				a.setUsername(rs.getString("username"));
				a.setName(rs.getString("accName"));
				a.setBalance(rs.getDouble("balance"));
				a.setApproved(Boolean.parseBoolean(rs.getString("isApprove")));
				
				accounts.add(a);
			}
			
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.getMessage();
		} catch (IOException e) {
			e.getMessage();
		}
		
		return accounts;
	}

	/******************************************************************/
	
	@Override
	public List<Account> getAccountsByUserName(String username) {
		List<Account> accounts = new ArrayList<>();
		
		try {
			conn = DAOUtil.getConnection();
			String sql = "SELECT * FROM ACCOUNTS WHERE username LIKE ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Account a = new Account();
				
				a.setNumber(rs.getLong("accNumber"));
				a.setUsername(rs.getString("username"));
				a.setName(rs.getString("accName"));
				a.setBalance(rs.getDouble("balance"));
				a.setApproved(Boolean.parseBoolean(rs.getString("isApprove")));
				
				accounts.add(a);
			}
			
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.getMessage();
		} catch (IOException e) {
			e.getMessage();
		}
		
		return accounts;
	}
	
	/******************************************************************/
	
	@Override
	public List<Account> getAccountsByNumber(Long accountNumber) {
		List<Account> accounts = null;

		try {
			conn = DAOUtil.getConnection();
			String sql = "SELECT * FROM ACCOUNTS WHERE accNumber LIKE ?";
			ps = conn.prepareStatement(sql);
			ps.setLong(1, accountNumber);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Account a = new Account();
				
				a.setNumber(rs.getLong("accNumber"));
				a.setUsername(rs.getString("username"));
				a.setName(rs.getString("accName"));
				a.setBalance(rs.getDouble("balance"));
				a.setApproved(Boolean.parseBoolean(rs.getString("isApprove")));
				
				accounts.add(a);
			}
			
			
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.getMessage();
		} catch (IOException e) {
			e.getMessage();
		}
		
		return accounts;
	}

	/******************************************************************/
	
	@Override
	public boolean addAccount(Account account) {
		try {
			conn = DAOUtil.getConnection();
			String sql = "INSERT INTO ACCOUNTS VALUES (?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setLong(1, account.getNumber());
			ps.setString(2, account.getUsername());
			ps.setString(3, account.getName());
			ps.setDouble(4, account.getBalance());
			ps.setBoolean(5, account.isApproved());
	
			if(ps.executeUpdate() != 0) {
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
	public boolean removeAccount(Account account) {
		try {
			conn = DAOUtil.getConnection();
			String sql = "DELETE FROM ACCOUNTS WHERE accNumber = ?";
			ps = conn.prepareStatement(sql);
			ps.setLong(1, account.getNumber());
			
			if(ps.executeUpdate() != 0) {
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
