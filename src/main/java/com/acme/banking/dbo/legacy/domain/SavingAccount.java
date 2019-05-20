package com.acme.banking.dbo.legacy.domain;

/** Structure modeled with class: no behavior encapsulated */
public class SavingAccount implements Account {
    public long id;
    public double amount;
    public double overdraft; //state

    public SavingAccount() {
    }

    public SavingAccount(long id, double amount, double overdraft) {
        this.id = id;
        this.amount = amount;
        this.overdraft = overdraft;
    }

    public SavingAccount(double amount, double overdraft) {
        this.amount = amount;
        this.overdraft = overdraft;
    }

    @Override
    public long getId() {
        return 0;
    }

    @Override
    public double getAmount() {
        return 0;
    }

    @Override
    public void withdraw(double amount) {

    }

    @Override
    public void deposit(double amount) {

    }
}
