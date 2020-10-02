package com.acme.offirent.domain.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "currencies")
public class Currency  extends AuditModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;

    @NotNull
    @Size(max = 30)
    @NaturalId
    private String name;

    @NotNull
    private Character symbol;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            mappedBy = "currencies")

    @JsonIgnore
    private List<Country> countries;

    public Currency(){

    }
    public Currency(@NotNull @Size(max = 30) String name, @NotNull Character symbol){
        this.name = name;
        this.symbol = symbol;
    }


    public Long getId(){
        return id;
    }
    public Currency setId(){
        this.id = id;
        return this;
    }
    public String getName(){
        return name;
    }
    public Currency setName(){
        this.name = name;
        return this;
    }
    public Character getSymbol(){
        return symbol;
    }
    public Currency setSymbol(){
        this.symbol = symbol;
        return this;
    }
    public List<Country> getCountries(){
        return countries;
    }
    public Currency setCountries(List<Country> countries){
        this.countries = countries;
        return this;
    }
}
