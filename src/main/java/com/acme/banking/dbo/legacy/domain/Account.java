package com.acme.banking.dbo.legacy.domain;

public interface Account {
    long getId();
    double getAmount();
    void withdraw(double amount);
    void deposit(double amount);
}
