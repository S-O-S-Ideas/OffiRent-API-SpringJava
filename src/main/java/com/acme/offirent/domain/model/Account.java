package com.acme.offirent.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "accounts")
public class Account{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String email;

    @NotNull
    private String password;

    @NotNull
    private String identification;

    @Lob
    @NotNull
    private Long accType;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private Long phone;

    @NotNull
    private boolean premium;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name ="account_payment_methods",
    joinColumns = {@JoinColumn(name = "account_id")},
    inverseJoinColumns = {@JoinColumn(name = "payment_method_id")})
    private List<PaymentMethod> paymentMethods;

    @OneToMany(mappedBy = "account")
    private List<Office> offices;

    /*@OneToMany(mappedBy = "account")
    private List<Reservation> reservations;*/

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "discount_id", nullable = false)
    @JsonIgnore
    private Discount discount;

    public Long getId() {
        return id;
    }

    public Account setId(Long id) {
        this.id = id;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Account setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public Account setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getIdentification() {
        return identification;
    }

    public Account setIdentification(String identification) {
        this.identification = identification;
        return this;
    }

    public Long getAccType() {
        return accType;
    }

    public Account setAccType(Long accType) {
        this.accType = accType;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public Account setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Account setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Long getPhone() {
        return phone;
    }

    public Account setPhone(Long phone) {
        this.phone = phone;
        return this;
    }

    public boolean isPremium() {
        return premium;
    }

    public Account setPremium(boolean premium) {
        this.premium = premium;
        return this;
    }

    public List<PaymentMethod> getPaymentMethods() {
        return paymentMethods;
    }

    public Account setPaymentMethods(List<PaymentMethod> payment_methods) {
        this.paymentMethods = payment_methods;
        return this;
    }

    public List<Office> getOffices() {
        return offices;
    }

    public Account setOffices(List<Office> offices) {
        this.offices = offices;
        return this;
    }

   /* public List<Reservation> getReservations() {
        return reservations;
    }*/

   /* public Account setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
        return this;
    }*/

    public Discount getDiscount() {
        return discount;
    }

    public Account setDiscount(Discount discount) {
        this.discount = discount;
        return this;
    }
}