package com.acme.offirent.resource;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class SavePaymentMethodResource {

    @NotNull
    @NotBlank
    private Long id;

    @NotNull
    @NotBlank
    private String card_number;

    @NotNull
    private String owner_name;

    @NotNull
    @NotBlank
    private String due_date;

    @NotNull
    @NotBlank
    private String cv;
}
