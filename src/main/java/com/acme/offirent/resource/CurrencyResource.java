package com.acme.offirent.resource;

public class CurrencyResource  {
    private Long id;
    private String name;
    private Character symbol;

    public Long getId(){
        return id;
    }
    public CurrencyResource setId(Long id){
        this.id = id;
        return this;
    }
    public String getName(){
        return name;
    }
    public CurrencyResource setName(String name){
        this.name = name;
        return this;
    }
    public Character getSymbol(){
        return symbol;
    }
    public CurrencyResource setSymbol(Character symbol){
        this.symbol = symbol;
        return this;
    }
}
