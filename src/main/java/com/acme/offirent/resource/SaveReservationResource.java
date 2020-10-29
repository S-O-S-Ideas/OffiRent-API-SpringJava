package com.acme.offirent.resource;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class SaveReservationResource {

    @NotNull
    private Boolean status;

    @NotNull
    @NotBlank
    private Date initialDate;

    @NotNull
    @NotBlank
    private Date endDate;



}
