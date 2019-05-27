package com.acme.banking.dbo.spring.service;

import com.acme.banking.dbo.spring.dao.AccountRepository;
import com.acme.banking.dbo.spring.domain.Account;
import com.acme.banking.dbo.spring.domain.CheckingAccount;
import com.acme.banking.dbo.spring.domain.SavingAccount;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@ContextConfiguration({"classpath:test-spring-context.xml", "classpath:spring-context.xml"})
@ActiveProfiles({"test","system-test"})
public class TransferServiceTest {
    @Autowired
    private TransferService transferService;
    @MockBean
    private AccountRepository accounts; //mock(StubAccountRepository.class)
    private long FROM_ACCOUNT_ID = 1;
    private long TO_ACCOUNT_ID = 2;

    @Test
    public void shouldTransferAmountNotMoreThanExistOnFromAccountBetweenExistingAccounts(){
        Account accountFrom = mock(Account.class);
        Account accountTo = mock(Account.class);
        when(accounts.findById(FROM_ACCOUNT_ID)).thenReturn(Optional.of(accountFrom));
        when(accounts.findById(TO_ACCOUNT_ID)).thenReturn(Optional.of(accountTo));
        transferService.transfer(1,2,100);
        verify(accountFrom, times(1)).setAmount(anyDouble());
        verify(accountTo, times(1)).setAmount(anyDouble()   );
        verify(accountFrom, times(1)).getAmount();
        verify(accountTo, times(1)).getAmount();
    }

    @Test(expected = IllegalStateException.class)
    public void shouldThrowExceptionInCaseIfFromAccountIsNotPresent(){
        Account accountFrom = mock(Account.class);
        Account accountTo = mock(Account.class);
        when(accounts.findById(FROM_ACCOUNT_ID)).thenReturn(Optional.of(accountFrom));
        when(accounts.findById(TO_ACCOUNT_ID)).thenReturn(Optional.of(accountTo));
        transferService.transfer(3, 2, 100);
    }

    @Test(expected = IllegalStateException.class)
    public void shouldThrowExceptionInCaseIfToAccountIsNotPresent(){
        Account accountFrom = mock(Account.class);
        Account accountTo = mock(Account.class);
        when(accounts.findById(FROM_ACCOUNT_ID)).thenReturn(Optional.of(accountFrom));
        when(accounts.findById(TO_ACCOUNT_ID)).thenReturn(Optional.of(accountTo));
        transferService.transfer(1, 3, 100);
    }
}
