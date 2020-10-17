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

    public String getAddress() {
        return address;
    }

    public SaveOfficeResource setAddress(String address) {
        this.address = address;
        return this;
    }

    public Long getFloor() {
        return floor;
    }

    public SaveOfficeResource setFloor(Long floor) {
        this.floor = floor;
        return this;
    }

    public Long getCapacity() {
        return capacity;
    }

    public SaveOfficeResource setCapacity(Long capacity) {
        this.capacity = capacity;
        return this;
    }

    public Boolean getAllowResource() {
        return allowResource;
    }

    public SaveOfficeResource setAllowResource(Boolean allowResource) {
        this.allowResource = allowResource;
        return this;
    }

    public Float getScore() {
        return score;
    }

    public SaveOfficeResource setScore(Float score) {
        this.score = score;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public SaveOfficeResource setDescription(String description) {
        this.description = description;
        return this;
    }

    public Float getPrice() {
        return price;
    }

    public SaveOfficeResource setPrice(Float price) {
        this.price = price;
        return this;
    }

    public Boolean getStatus() {
        return status;
    }

    public SaveOfficeResource setStatus(Boolean status) {
        this.status = status;
        return this;
    }

    public String getComment() {
        return comment;
    }

    public SaveOfficeResource setComment(String comment) {
        this.comment = comment;
        return this;
    }
}
