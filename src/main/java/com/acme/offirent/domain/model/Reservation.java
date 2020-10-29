package com.acme.offirent.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;


@Entity
@Table(name="reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Boolean status;

    @NotNull
    private Date initialDate;

    @NotNull
    private Date endDate;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "account_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Account account;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "office_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Office office;

    public Long getId(){
        return id;
    }

    public Reservation setId(Long id){
        this.id = id;
        return this;
    }
    public Boolean getStatus(){
        return status;
    }
    public Reservation setStatus(Boolean status){
        this.status = status;
        return this;
    }
    public Date getInitialDate(){
        return initialDate;
    }
    public Reservation setInitialDate(Date initialDate){
        this.initialDate=initialDate;
        return this;
    }
    public Date getEndDate(){
        return endDate;
    }
    public Reservation setEndDate(Date endDate){
        this.endDate = endDate;
        return this;
    }
    public Account getAccount(){
        return account;
    }
    public Reservation setAccount(Account account){
        this.account = account;
        return this;
    }
    public Office getOffice(){
        return office;
    }
    public Reservation setOffice(Office office){
        this.office = office;
        return this;
    }
}