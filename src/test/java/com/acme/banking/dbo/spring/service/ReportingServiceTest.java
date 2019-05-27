package com.acme.banking.dbo.spring.service;

import com.acme.banking.dbo.spring.dao.AccountRepository;
import com.acme.banking.dbo.spring.domain.Account;
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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@ContextConfiguration({"classpath:test-spring-context.xml", "classpath:spring-context.xml"})
@ActiveProfiles({"test","system-test"})
public class ReportingServiceTest {
    @Autowired
    private ReportingService reportingService;
    @MockBean
    private AccountRepository accounts; //mock(StubAccountRepository.class)
    @Value("${marker}") private String layoutMarker;
    private String ACCOUNT_TO_STRING = "testAccountToString";

    @Test
    public void shouldDoReportForExistingAccount(){
        Account accountFromStub = mock(Account.class);
        when(accountFromStub.getAmount()).thenReturn(100d);
        when(accounts.findById(1L)).thenReturn(Optional.of(accountFromStub));
        when(accountFromStub.toString()).thenReturn(ACCOUNT_TO_STRING);
        assertThat(reportingService.accountReport(1L))
                .isEqualTo(layoutMarker + layoutMarker + " " + ACCOUNT_TO_STRING);
    }

    @Test(expected = IllegalStateException.class)
    public void shouldThrowExceptionInCaseIfAccountIsNotPresent(){
        reportingService.accountReport(2L);
    }
}
