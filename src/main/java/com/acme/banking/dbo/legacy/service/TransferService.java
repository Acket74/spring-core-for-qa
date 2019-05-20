package com.acme.banking.dbo.legacy.service;

import com.acme.banking.dbo.ooad.domain.Account;

public class TransferService {
    public void transfer(com.acme.banking.dbo.ooad.domain.Account from, Account to, double amount) {
        from.withdraw(amount);
        to.deposit(amount);
    }
}
