package com.revature.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Employee;
import com.revature.models.User;
import com.revature.util.DAOUtil;

public class EmployeeDAOImpl implements EmployeeDAO {
	
	Connection conn = null;
	PreparedStatement ps = null;
	
    public List<User> getAllUsers() {
		User u = null;
		List<User> users = new ArrayList<>();
		
		try {
			conn = DAOUtil.getConnection();
			String sql = "SELECT username, pwrd, firstName, lastName FROM EMPLOYEES UNION SELECT username, pwrd, firstName, lastName FROM CUSTOMERS";
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				String username = rs.getString("username");
				String pwrd = rs.getString("pwrd");
				String first = rs.getString("firstName");
				String last = rs.getString("lastName");
				
				u = new User(username, pwrd, first, last);
				users.add(u);
			}
			
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.getMessage();
		} catch (IOException e) {
			e.getMessage();
		}
		
		return users;
    }
    
    /******************************************************************/
    
    public List<Employee> getAllEmployees() {
		Employee e = null;
		List<Employee> employees = new ArrayList<>();
		
		try {
			conn = DAOUtil.getConnection();
			String sql = "SELECT *	FROM EMPLOYEES";
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				String username = rs.getString("username");
				String pwrd = rs.getString("pwrd");
				String first = rs.getString("firstName");
				String last = rs.getString("lastName");
				boolean isAdmin = Boolean.parseBoolean(rs.getString("isAdmin"));
				
				e = new Employee(username, pwrd, first, last, isAdmin);
				employees.add(e);
			}
			
			rs.close();
			ps.close();
		} catch (SQLException ex) {
			ex.getMessage();
		} catch (IOException ex) {
			ex.getMessage();
		}
		
		return employees;
    }
    
    /******************************************************************/
    
    public boolean grantAdminPriviledge(Employee employee) {
        try {
			conn = DAOUtil.getConnection();
			String sql = "UPDATE EMPLOYEES SET isAdmin = true WHERE username = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, employee.getUsername());
			
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
    
    public boolean revokeAdminPriviledge(Employee employee) {
         try {
			conn = DAOUtil.getConnection();
			String sql = "UPDATE EMPLOYEES SET isAdmin = false WHERE username = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, employee.getUsername());
			
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