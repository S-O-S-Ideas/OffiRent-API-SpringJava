package com.acme.offirent.domain.service;

import com.acme.offirent.domain.model.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface AccountService {
    Page <Account> getAllAccounts(Pageable pageable);
    Account getAccountById (Long accountId);
    Account createAccount(Account account);
    Account updateAccount(Long accountId, Account accountRequest);
    Account updateAccountStatus(Long accountId,Account accountRequest);
    ResponseEntity<?> deleteAccount(Long accountId);
}
