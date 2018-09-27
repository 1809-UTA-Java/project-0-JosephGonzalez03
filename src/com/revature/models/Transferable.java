package com.revature.models;

public interface Transferable {
    boolean transferMoney(Account dest, double amount);
}