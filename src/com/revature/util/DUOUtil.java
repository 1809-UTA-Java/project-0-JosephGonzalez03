package com.revature.util;

import com.revature.duo.*;

public class DUOUtil {
	public static AdminDUO getAdminDUO() {
	    return new AdminDUO();
	}
	
	public static EmployeeDUO getEmployeeDUO() {
	    return new EmployeeDUO();
	}
	
	public static CustomerDUO getCustomerDUO() {
	    return new CustomerDUO();
	}
}