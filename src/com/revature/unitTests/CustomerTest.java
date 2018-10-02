package com.revature.unitTests;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import com.revature.models.*;

public class CustomerTest {
	@Test
	public void despositTest() {
		Account a = new Account("Checking", "111");
		Customer c = new Customer("JosephG", "password", "Joseph", "G");

		double amount = 10;
		
		c.depositMoney(a, amount);
		
		double expectedBalance = 10;
		double actualBalance = a.getBalance();
		
		assertEquals(expectedBalance, actualBalance, 0.00);
		
		// no change should occur, warning will be printed for user
		expectedBalance = 10;
		actualBalance = a.getBalance();
		
		assertEquals(expectedBalance, actualBalance, 0.00);
	}
	
	@Test
	public void withdrawalTest() {
		Account a = new Account("Checking", "111");
		Customer c = new Customer("JosephG", "password", "Joseph", "G");
		
		double amount = 11;

		a.setBalance(10);
		c.withdrawMoney(a, amount);
		
		// no change should occur, warning will be printed for user
		double expectedBalance = 10;
		double actualBalance = a.getBalance();
		
		assertEquals(expectedBalance, actualBalance, 0.00);
		
		/*************************************************************/
		amount = -8;
		
		a.setBalance(10);
		c.withdrawMoney(a, amount);
	
		// no change should occur, warning will be printed for user
		expectedBalance = 10;
		actualBalance = a.getBalance();
		
		assertEquals(expectedBalance, actualBalance, 0.00);
		
		/*************************************************************/
		amount = 8;
		
		a.setBalance(10);
		c.withdrawMoney(a, amount);
		
		expectedBalance = 2;
		actualBalance = a.getBalance();
		
		assertEquals(expectedBalance, actualBalance, 0.00);
	} 
	
	@Test
	public void transferTest() {
		Account a1 = new Account("Checking", "111");
		Account a2 = new Account("Savings", "112");
		Customer c = new Customer("JosephG", "password", "Joseph", "G");
		
		double amount = 11;

		a1.setBalance(10);
		c.transferMoney(a1, a2, amount);
		
		// no change should occur, warning will be printed for user
		double expectedBalance = 10;
		double actualBalance = a1.getBalance();
		
		assertEquals(expectedBalance, actualBalance, 0.00);
		
		expectedBalance = 0;
		actualBalance = a2.getBalance();
		
		assertEquals(expectedBalance, actualBalance, 0.00);
		
		/*************************************************************/
		amount = -8;
		
		a1.setBalance(10);
		c.transferMoney(a1, a2, amount);
		
		// no change should occur, warning will be printed for user
		expectedBalance = 10;
		actualBalance = a1.getBalance();
				
		assertEquals(expectedBalance, actualBalance, 0.00);
		
		expectedBalance = 0;
		actualBalance = a2.getBalance();
		
		assertEquals(expectedBalance, actualBalance, 0.00);
		
		/*************************************************************/
		amount = 10;
		
		a1.setBalance(10);
		c.transferMoney(a1, a2, amount);
		
		// no change should occur, warning will be printed for user
		expectedBalance = 0;
		actualBalance = a1.getBalance();
				
		assertEquals(expectedBalance, actualBalance, 0.00);
		
		expectedBalance = 10;
		actualBalance = a2.getBalance();
		
		assertEquals(expectedBalance, actualBalance, 0.00);
	}
}
