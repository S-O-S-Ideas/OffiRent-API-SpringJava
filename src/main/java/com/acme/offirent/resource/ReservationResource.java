package com.acme.offirent.resource;

import com.acme.offirent.domain.model.Reservation;

import java.util.Date;

public class ReservationResource {

    private Long id;
    private Boolean status;
    private Date initialDate;
    private Date endDate;

    public Long getId(){
        return id;
    }

    public ReservationResource setId(Long id){
        this.id = id;
        return this;
    }
    public Boolean getStatus(){
        return status;
    }
    public ReservationResource setStatus(Boolean status){
        this.status = status;
        return this;
    }
    public Date getInitialDate(){
        return initialDate;
    }
    public ReservationResource setInitialDate(Date initialDate){
        this.initialDate=initialDate;
        return this;
    }
    public Date getEndDate(){
        return endDate;
    }
    public ReservationResource setEndDate(Date endDate){
        this.endDate = endDate;
        return this;
    }

}
