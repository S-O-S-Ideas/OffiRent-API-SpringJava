package com.acme.offirent.resource;

import com.acme.offirent.domain.model.AuditModel;

public class CountryResource extends AuditModel {

    private Long id;
    private String name;

    public Long getId(){
        return id;
    }

    public CountryResource setId(Long id){
        this.id = id;
        return this;
    }

    public String getName(){
        return name;
    }

    public CountryResource setName(String name) {
        this.name = name;
        return this;
    }
}
