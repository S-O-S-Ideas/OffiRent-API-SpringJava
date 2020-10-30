package com.acme.offirent;


import com.acme.offirent.domain.model.Account;
import com.acme.offirent.domain.model.District;
import com.acme.offirent.domain.model.Office;
import com.acme.offirent.domain.model.Reservation;
import com.acme.offirent.domain.repository.AccountRepository;
import com.acme.offirent.domain.repository.DistrictRepository;
import com.acme.offirent.domain.repository.OfficeRepository;
import com.acme.offirent.domain.repository.ReservationRepository;
import com.acme.offirent.domain.service.AccountService;
import com.acme.offirent.domain.service.OfficeService;
import com.acme.offirent.domain.service.ReservationService;
import com.acme.offirent.exception.ResourceNotFoundException;
import com.acme.offirent.service.AccountServiceImpl;
import com.acme.offirent.service.OfficeServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.*;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class AccountServiceImplIntegrationTest {

    @MockBean
    private AccountRepository accountRepository;

    @Autowired
    private AccountService accountService;


    @TestConfiguration
    static class AccountServiceImplTestConfiguration{

        @Bean
        public AccountService accountService(){
            return new AccountServiceImpl();
        }
    }

    @Test
    @DisplayName("When GetAccountById With Valid Id Then Returns Account")
    public void whenGetAccountByIdWithValidIdThenReturnsAccount(){

        //Arrange
        Long id = 1L;
        Account account= new Account();
        account.setId(id);


        when(accountRepository.findById(account.getId()))
                .thenReturn(Optional.of(account));
        //Act
        Account searchedAccount = accountService.getAccountById(id);
        //Assert
        assertThat(searchedAccount.getId()).isEqualTo(id);
    }

    @Test
    @DisplayName("When GetAccountById With Invalid Id Then Returns Resource Not Found Exception")
    public void whenGetAccountByIdWithInvalidIdThenReturnsResourceNotFoundException(){

        //Arrange
        Long invalidId = 1L;
        String template ="Resource %s not found for %s with value %s";
        String expectedMessage = String.format(template,"Office","Id",invalidId);

        when(accountRepository.findById(invalidId))
                .thenReturn(Optional.empty());

        //Act
        Throwable exception= catchThrowable(()->{
            Account searchedAccount = accountService.getAccountById(invalidId);
        });

        //Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }




}
