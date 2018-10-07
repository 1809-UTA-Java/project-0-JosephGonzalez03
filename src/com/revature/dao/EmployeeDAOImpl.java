package com.revature.dao;

import java.util.List;

import com.revature.models.Employee;

public interface EmployeeDAO {
    public List<User> getAllUsers() {
		Employee e = null;
		List<Employee> employees = new ArrayList<>();
		
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
    
    public boolean grantAdminPriviledge(Employee employee) {
        try {
			conn = DAOUtil.getConnection();
			String sql = "UPDATE EMPLOYEES SET isAdmin = true WHERE username = ?";
			ps = conn.prepareStatement(sql);
			ps.setDouble(1, employee.getUsername());
			
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
			ps.setDouble(1, employee.getUsername());
			
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