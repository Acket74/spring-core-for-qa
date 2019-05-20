package com.acme.banking.dbo.legacy.dal;

import com.acme.banking.dbo.legacy.domain.SavingAccount;
import com.acme.banking.dbo.ooad.domain.Account;

import java.util.Comparator;

public interface AccountRepository {
    Account findById(long id);
    }
