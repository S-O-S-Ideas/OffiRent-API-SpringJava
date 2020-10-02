package com.acme.offirent.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;


@Entity
@Table(name = "countries")

public class Country extends AuditModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Size(max = 30)
    @Column(unique = true)
    private String name;


    @ManyToMany(fetch = FetchType.LAZY,
    cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "country_currencies",
            joinColumns = {@JoinColumn(name = "country_id")},
            inverseJoinColumns = {@JoinColumn(name = "currency_id")})

    @JsonIgnore
    private List<Currency> currencies;

    public Country(@NotNull @Size(max = 30) String name) {
        this.name = name;
    }

    public Country() {

    }
    public boolean isCurrencyWith(Currency currency){
        return this.getCurrencies().contains(currency);
    }
    public Country currencyWith(Currency currency){
        if (!this.isCurrencyWith(currency))
            this.getCurrencies().add(currency);
        return this;
    }
    public Country unCurrencyWith(Currency currency){
        if (this.isCurrencyWith(currency))
            this.getCurrencies().remove(currency);
        return this;
    }

    public Long getId() {
        return id;
    }

    public Country setId() {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Country setName() {
        this.name = name;
        return this;
    }

    public List<Currency> getCurrencies() {
        return currencies;
    }

    public Country setCountries(List<Currency> currencies) {
        this.currencies = currencies;
        return this;
    }
}
