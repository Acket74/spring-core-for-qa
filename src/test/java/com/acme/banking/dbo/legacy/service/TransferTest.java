package com.acme.banking.dbo.legacy.service;

import com.acme.banking.dbo.legacy.domain.Account;
import org.fest.assertions.data.Offset;
import org.junit.Before;
import org.junit.Test;

import static com.acme.banking.dbo.legacy.domain.AccountType.CHECKING;
import static com.acme.banking.dbo.legacy.domain.AccountType.SAVING;
import static org.fest.assertions.api.Assertions.assertThat;

public class TransferTest {
    @Before
    public void resetAccounts() {
        AccountRepository.reset();
    }

    @Test
    public void shouldTransferWhenSavingAccount() {
        AccountRepository.save(new Account(SAVING, 100, 0));
        AccountRepository.save(new Account(SAVING, 200, 0));
        Account from = AccountRepository.findById(1L);
        Account to = AccountRepository.findById(2L);

        TransferService.transfer(from, to, 100d);

        assertThat(from.amount).isEqualTo(0, Offset.offset(1d));
        assertThat(to.amount).isEqualTo(300, Offset.offset(1d));
    }

    @Test
    public void shouldTransferWhenCheckingAccount() {
        AccountRepository.save(new Account(CHECKING, 100, 100));
        AccountRepository.save(new Account(CHECKING, 200, 100));
        Account from = AccountRepository.findById(1L);
        Account to = AccountRepository.findById(2L);

        TransferService.transfer(from, to, 200);

        assertThat(from.amount).isEqualTo(-100, Offset.offset(1d));
        assertThat(to.amount).isEqualTo(400, Offset.offset(1d));
    }
}
