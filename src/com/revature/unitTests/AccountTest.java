package com.revature.unitTests;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import com.revature.models.*;

public class AccountTest {
	@Test
	public void getInfoTest() {
		
		String name = "Joseph";
		String number = "1111111";
		
		Account a = new Account(name);

		String expectedString = "****** ACCOUNT INFORMATION ******\n"
                + "Account Name: " + name + "\n"
                + "Account Number: " + number + "\n\n";
		
		String actualString = a.getInfo();
		
		assertEquals(expectedString, actualString);
	}
}
