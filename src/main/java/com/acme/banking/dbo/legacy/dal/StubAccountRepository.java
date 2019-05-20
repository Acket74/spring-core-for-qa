package com.acme.banking.dbo.legacy.dal;

import com.acme.banking.dbo.ooad.dal.AccountRepository;
import com.acme.banking.dbo.ooad.domain.Account;
import com.acme.banking.dbo.ooad.domain.SavingAccount;

public class StubAccountRepository implements AccountRepository {
    private double amount;

    public StubAccountRepository(double amount) {
        this.amount = amount;
    }

    @Override
    public Account findById(long id) {
        return new SavingAccount(0, amount);
    }
}

