package com.acme.offirent.resource;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SaveCurrency {

    @NotNull
    @Size(max = 30)
    private String name;

    @NotNull
    private Character symbol;

    public String getName(){
        return name;
    }

    public SaveCurrency setName(String name){
        this.name = name;
        return this;
    }

    public Character getSymbol(){
        return symbol;
    }

    public SaveCurrency setSymbol(Character symbol){
        this.symbol = symbol;
        return this;
    }
}
