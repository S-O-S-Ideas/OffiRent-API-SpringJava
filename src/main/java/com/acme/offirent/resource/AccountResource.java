package com.acme.offirent.resource;

import com.acme.offirent.domain.model.Account;

public class AccountResource {

    private Long id;
    private String email;
    private String password;
    private String identification;
    private Boolean isPremium;
    private String firstName;
    private String lastName;
    private String phone;

    public Long getId() {
        return id;
    }

    public AccountResource setId(Long id) {
        this.id = id;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public AccountResource setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public AccountResource setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getIdentification() {
        return identification;
    }

    public AccountResource setIdentification(String identification) {
        this.identification = identification;
        return this;
    }

    public Boolean getIsPremium() {
        return isPremium;
    }

    public AccountResource setIsPremium(Boolean isPremium) {
        this.isPremium = isPremium;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public AccountResource setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public AccountResource setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public AccountResource setPhone(String phone) {
        this.phone = phone;
        return this;
    }

}
