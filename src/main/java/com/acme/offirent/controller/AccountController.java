package com.acme.offirent.controller;

import com.acme.offirent.domain.model.Account;
import com.acme.offirent.domain.service.AccountService;
import com.acme.offirent.resource.AccountResource;
import com.acme.offirent.resource.SaveAccountResource;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
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
public class AccountController{

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private AccountService accountService;


    @Operation(summary = "Get Accounts", description = "Get all Accounts by Pages", tags = {"accounts"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "100", description = "All Accounts returned", content = @Content
                    (mediaType = "application/json"))
    })
    @GetMapping("/accounts")
    public Page<AccountResource> getAllAccounts(Pageable pageable){

        List<AccountResource> accounts = accountService.getAllAccounts(pageable)
                .getContent().stream().map(this::convertToResource)
                .collect(Collectors.toList());

        int accountsCount = accounts.size();
        return new PageImpl<>(accounts, pageable, accountsCount);
    }

    @Operation(summary = "Get Account by Id", description = "Get Account for given Id", tags = {"accounts"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "100", description = "Account returned", content = @Content
                    (mediaType = "application/json"))
    })
    @GetMapping("/accounts/{id}")
    public AccountResource getAccountById(@PathVariable(name="id") Long accountId){
        return convertToResource(accountService.getAccountById(accountId));
    }

    @Operation(summary = "Create Accounts ",description = "Create a new Account",tags = {"accounts"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "100", description = "Create a new Account for given information",content =@Content(mediaType = "application/json") )
    })
    @PostMapping("/accounts")
    public AccountResource createAccount(@Valid @RequestBody SaveAccountResource resource){
        return convertToResource(
                accountService.createAccount(convertToEntity(resource)));
    }

    @Operation(summary = "Update Accounts",description = "Update Account for given Id",tags = {"accounts"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "100", description = "Update information of account for given Id",content =@Content(mediaType = "application/json") )
    })
    @PutMapping("/account/{id}")
    public AccountResource updateAccount(@PathVariable(name = "id")   Long accountId,@Valid @RequestBody SaveAccountResource resource){
        return convertToResource(accountService.updateAccount(accountId,convertToEntity(resource)));
    }

    @Operation(summary = "Delete Accounts",description = "Delete Account for given Id",tags = {"accounts"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "100", description = "Delete Account for given Id",content =@Content(mediaType = "application/json") )
    })
    @DeleteMapping("/accounts/{id}")
    public ResponseEntity<?> deleteAccount(@PathVariable(name="id") Long accountId){
        return accountService.deleteAccount(accountId);
    }

    private Account convertToEntity(SaveAccountResource resource){
        return mapper.map(resource, Account.class);
    }

    private AccountResource convertToResource(Account entity){
        return mapper.map(entity, AccountResource.class);
    }
}