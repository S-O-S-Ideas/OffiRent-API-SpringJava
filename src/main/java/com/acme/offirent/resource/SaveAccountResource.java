package com.acme.offirent.resource;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SaveAccountResource {

    @NotNull
    @NotBlank
    @Size(max = 50)
    private String email;

    @NotNull
    @NotBlank
    @Size(max = 50)
    private String password;

    @NotNull
    @NotBlank
    @Size(max = 100)
    private String identification;

    @NotNull
    private Boolean isPremium;

    @NotNull
    @NotBlank
    @Size(max = 50)
    private String firstName;

    @NotNull
    @NotBlank
    @Size(max = 50)
    private String lastName;

    @NotNull
    @NotBlank
    @Size(max = 50)
    private String phone;
}
