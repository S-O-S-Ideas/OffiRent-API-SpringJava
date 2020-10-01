package com.acme.offirent.resource;

import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class SaveOfficeResource {

    @NotNull
    private String address;

    @NotNull
    private Long floor;

    @NotNull
    private Long capacity;

    @NotNull
    private Boolean allowResource;

    @NotNull
    private Float score;

    @Lob
    @NotNull
    private String description;

    @NotNull
    private Float price;

    @NotNull
    private Boolean status;

    @Lob
    @NotNull
    private String comment;
}
