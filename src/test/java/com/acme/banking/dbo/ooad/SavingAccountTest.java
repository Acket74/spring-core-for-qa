package com.acme.banking.dbo.ooad;

import com.acme.banking.dbo.ooad.domain.Account;
import com.acme.banking.dbo.ooad.domain.SavingAccount;
import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Mockito.mock;

public class SavingAccountTest {
    @Test
    public void shouldReturnIdForCreatedAccount(){
        long TEST_ID = 0;
        double TEST_AMOUNT = 1000.;
        Account stubAccountWithId = new SavingAccount(TEST_ID, 100.);
        Assert.assertEquals("Wrong ID is returned for SavingAccount", TEST_ID, stubAccountWithId.getId());
    }

    @Test
    public void shouldReturnAmountForCreatedAccount(){
        double TEST_AMOUNT = 1000.;
        long TEST_ID = 0;
        Account stubAccountWithAmount = new SavingAccount(TEST_ID, TEST_AMOUNT);
        Assert.assertEquals(TEST_AMOUNT, stubAccountWithAmount.getAmount(), 0);
    }

    @Test
    public void shouldDecreaseAmountAfterWithdraw(){
        double TEST_AMOUNT = 1000.;
        double WITHDRAW_AMOUNT = 300.;
        long TEST_ID = 0;
        Account stubAccountWithAmount = new SavingAccount(TEST_ID, TEST_AMOUNT);
        stubAccountWithAmount.withdraw(WITHDRAW_AMOUNT);
        Assert.assertEquals(TEST_AMOUNT-WITHDRAW_AMOUNT, stubAccountWithAmount.getAmount(), 0);
    }
}
