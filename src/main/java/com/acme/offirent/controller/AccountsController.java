package com.acme.offirent.controller;

import com.acme.offirent.domain.model.Account;
import com.acme.offirent.domain.service.AccountService;
import com.acme.offirent.resource.AccountResource;
import com.acme.offirent.resource.SaveAccountResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class AccountsController {


    @Autowired
    private ModelMapper mapper;

    @Autowired
    private AccountService accountService;

    @Operation(summary = "Get Accounts", description = "Get All Accounts", tags = {"accounts"})
    @ApiResponses(value= {
            @ApiResponse(responseCode = "200", description = "All Accounts returned",content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/accounts")
    public Page<AccountResource> getAllAccounts(Pageable pageable){
        Page<Account> accountsPage = accountService.getAllAccounts(pageable);
        List<AccountResource> resources = accountsPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources,pageable,resources.size());
    }

    @Operation(summary = "Get Account By Id", description = "Get Account for given Id", tags = {"accounts"})
    @GetMapping("/accounts/{id}")
    public AccountResource getAccountById(@PathVariable(name="id") Long accountId){
        return convertToResource(accountService.getAccountById(accountId));
    }


    @Operation(summary = "Create an Account",description = "Create a new Account", tags = {"accounts"})
    @PostMapping("/accounts")
    public AccountResource createAccount(@Valid @RequestBody SaveAccountResource resource){
        Account account = convertToEntity(resource);
        return convertToResource(accountService.createAccount(account));
    }

    @Operation(summary = "Update an Account", description = "Update an Account for given Id", tags = {"accounts"})
    @PutMapping("/accounts/{accountId}")
    public AccountResource updateAccount(@PathVariable Long accountId,@RequestBody SaveAccountResource resource){
        Account account = convertToEntity(resource);
        return convertToResource(accountService.updateAccount(accountId,account));
    }

    @Operation(summary = "Update an Account to Premium Status", description = "Update an Account to Premium Status for given Id",tags = {"accounts"})
    @PatchMapping("/accounts/{accountId}")
    public AccountResource updateToPremium(@PathVariable Long accountId, @RequestBody SaveAccountResource resource){
        Account account = convertToEntity(resource);
        return convertToResource(accountService.updateAccountStatus(accountId,account));
    }

    @Operation(summary = "Delete an Account", description = "Delete an Account with given Id",tags = {"accounts"})
    @DeleteMapping("/accounts/{accountId}")
    public ResponseEntity<?> deleteAccount(@PathVariable Long accountId){
        return accountService.deleteAccount(accountId);
    }


    private Account convertToEntity(SaveAccountResource resource){
        return mapper.map(resource, Account.class);
    }
    private AccountResource convertToResource(Account entity){
        return mapper.map(entity,AccountResource.class);
    }


}
