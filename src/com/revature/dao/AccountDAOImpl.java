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
			String sql = "SELECT * FROM ACCOUNTS ORDER BY accNumber ASC";
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				int accNum = rs.getInt("accNumber");
				String name = rs.getString("accName");
				double balance = rs.getDouble("balance");
				boolean isApp = Boolean.parseBoolean(rs.getString("isApproved"));
				
				Account a = new Account(accNum, name, balance, isApp);
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
	public List<Account> getUnapprovedAccounts() {
	List<Account> accounts = new ArrayList<>();
		
		try {
			conn = DAOUtil.getConnection();
			String sql = "SELECT * FROM ACCOUNTS WHERE isApproved = 'false' ORDER BY accNumber ASC";
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				int accNum = rs.getInt("accNumber");
				String name = rs.getString("accName");
				double balance = rs.getDouble("balance");
				boolean isApp = Boolean.parseBoolean(rs.getString("isApproved"));
				
				Account a = new Account(accNum, name, balance, isApp);
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
	public List<Account> getAccountsByUsername(String username) {
		List<Account> accounts = new ArrayList<>();
		
		try {
			conn = DAOUtil.getConnection();
			String sql = "SELECT * FROM ACCOUNTS a INNER JOIN CUSTOMERS_ACCOUNTS ca ON a.accNumber = ca.accNumber WHERE ca.username = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				int accNum = rs.getInt("accNumber");
				String name = rs.getString("accName");
				double balance = rs.getDouble("balance");
				boolean isApp = Boolean.parseBoolean(rs.getString("isApproved"));
				
				Account a = new Account(accNum, name, balance, isApp);
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
	public Account getAccountByNumber(int accountNumber) {
		Account a = new Account();

		try {
			conn = DAOUtil.getConnection();
			String sql = "SELECT * FROM ACCOUNTS WHERE accNumber = ?";
			ps = conn.prepareStatement(sql);
			ps.setLong(1, accountNumber);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				a.setNumber(rs.getInt("accNumber"));
				a.setName(rs.getString("accName"));
				a.setBalance(rs.getDouble("balance"));
				a.setApproved(Boolean.parseBoolean(rs.getString("isApproved")));
			}
			
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.getMessage();
		} catch (IOException e) {
			e.getMessage();
		}
		
		return a;
	}
	
	/******************************************************************/
	
	@Override
	public Account getAccountByName(String accountName) {
		Account a = new Account();
		
		try {
			conn = DAOUtil.getConnection();
			String sql = "SELECT * FROM ACCOUNTS WHERE accName = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, accountName);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {			
				a.setNumber(rs.getInt("accNumber"));
				a.setName(rs.getString("accName"));
				a.setBalance(rs.getDouble("balance"));
				a.setApproved(Boolean.parseBoolean(rs.getString("isApproved")));
			}
			
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.getMessage();
		} catch (IOException e) {
			e.getMessage();
		}
		
		return a;
	}

	/******************************************************************/
	
	@Override
	public boolean addAccount(Account account) {
		try {
			conn = DAOUtil.getConnection();
			String sql = "INSERT INTO ACCOUNTS VALUES (?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, account.getNumber());
			ps.setString(2, account.getName());
			ps.setDouble(3, account.getBalance());
			ps.setString(4, Boolean.toString(account.isApproved()));
	
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
	public boolean addCustomerAccount(String username, Account account) {
		try {
			conn = DAOUtil.getConnection();
			String sql = "INSERT INTO CUSTOMERS_ACCOUNTS VALUES (?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setInt(2, account.getNumber());
	
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
			ps.setInt(1, account.getNumber());
			
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
	public boolean removeCustomerAccount(Account account) {
		try {
			conn = DAOUtil.getConnection();
			String sql = "DELETE FROM CUSTOMERS_ACCOUNTS WHERE accNumber = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, account.getNumber());
			
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
	public boolean updateBalance(int accountNumber, double balance) {
		try {
			conn = DAOUtil.getConnection();
			String sql = "UPDATE ACCOUNTS SET balance = ? WHERE accNumber = ?";
			ps = conn.prepareStatement(sql);
			ps.setDouble(1, balance);
			ps.setInt(2, accountNumber);
			
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

	@Override
	public boolean approveAccount(int accountNumber) {
		try {
			conn = DAOUtil.getConnection();
			String sql = "UPDATE ACCOUNTS SET isApproved = 'true' WHERE accNumber = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, accountNumber);
			
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
