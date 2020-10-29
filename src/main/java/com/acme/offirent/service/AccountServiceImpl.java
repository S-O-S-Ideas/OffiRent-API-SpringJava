package com.acme.offirent.service;
import com.acme.offirent.domain.model.Account;
import com.acme.offirent.domain.repository.AccountRepository;
import com.acme.offirent.domain.service.AccountService;
import com.acme.offirent.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService{

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Page <Account> getAllAccounts(Pageable pageable){
        return accountRepository.findAll(pageable);
    }

    @Override
    public Account getAccountById(Long accountId){
        return accountRepository.findById(accountId)
                .orElseThrow(()->
                        new ResourceNotFoundException("Account", "Id", accountId));
    }

    //TODO
   /* @Override
    public Page <Account> getAllAccountsByReservationId(Long reservationId, Pageable pageable){
        return reservationRepository.findById(reservationId).map( reservation -> {
            List<Account> accounts = reservation.getAccounts();
            int accountsCount = accounts.size();
            return new PageImpl<>(accounts, pageable, accountsCount);
        }).orElseThrow(()-> new ResourceNotFoundException("Reservation", "Id", reservationId));
    }*/
/*
    @Override
    public Page <Account> getAllAccountsByOfficeId(Long officeId, Pageable pageable){
        return officeRepository.findById(officeId).map( office -> {
            List<Account> accounts = office.getAccounts();
            int accountsCount = accounts.size();
            return new PageImpl<>(accounts, pageable, accountsCount);
        }).orElseThrow(()-> new ResourceNotFoundException("Office", "Id", officeId));
    }
*/
    @Override
    public Account createAccount (Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Account updateAccount(Long accountId, Account accountRequest) {
        return accountRepository.findById(accountId).map(account->{
            account.setEmail(accountRequest.getEmail());
            account.setPassword(accountRequest.getPassword());
            account.setIdentification(accountRequest.getIdentification());
            account.setAccType(accountRequest.getAccType());
            account.setFirstName(accountRequest.getFirstName());
            account.setLastName(accountRequest.getLastName());
            account.setPhone(accountRequest.getPhone());
            return accountRepository.save(account);
        }).orElseThrow(()->new ResourceNotFoundException("Account","Id",accountId));
    }

    @Override
    public ResponseEntity<?> deleteAccount(Long accountId) {
        return accountRepository.findById(accountId).map(account -> {
            accountRepository.delete(account);
            return ResponseEntity.ok().build();
        }).orElseThrow(()->new ResourceNotFoundException("Account","Id",accountId));
    }
}