package com.revature.dao;

import java.util.List;

import com.revature.models.User;
import com.revature.models.Employee;

public interface EmployeeDAO {
    public List<User> getAllUsers();
    public List<Employee> getAllEmployees();
    public Employee getEmployeeByUsername(String username);
    public boolean grantAdminPriviledge(Employee employee);
    public boolean revokeAdminPriviledge(Employee employee);
}