package com.acme.offirent.resource;

import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SaveAccountResource {

    @NotNull
    @NotBlank
    @Size(max = 30)
    private String email;

    @NotNull
    @NotBlank
    @Size(max = 30)
    private Long password;

    @NotNull
    @NotBlank
    private String identification;

    @Lob
    @NotNull
    @NotBlank
    private Long acc_type;

    @NotNull
    @NotBlank
    @Size(max = 30)
    private String first_name;

    @NotNull
    @NotBlank
    @Size(max = 30)
    private String last_name;

    @NotNull
    @Size(max = 30)
    private Long phone;
}
