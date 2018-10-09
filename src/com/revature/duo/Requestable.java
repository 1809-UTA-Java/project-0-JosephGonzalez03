package com.revature.duo;

import com.revature.models.Account;

public interface Requestable {
	boolean approveAccount(Account account);
	boolean denyAccount(Account account);
}
