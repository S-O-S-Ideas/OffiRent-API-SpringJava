package com.acme.offirent.resource;
import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SaveCountry {

    @NotNull
    @NotBlank
    @Size(max = 30)
    private String name;

    public String getName(){
        return name;
    }
    public SaveCountry setName(String name){
        this.name = name;
        return this;
    }

}
