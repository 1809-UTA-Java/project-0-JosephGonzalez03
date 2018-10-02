package com.revature.models;

public interface Requestable {
	boolean approveAccount(Account account);
	boolean denyAccount(Account account);
}
