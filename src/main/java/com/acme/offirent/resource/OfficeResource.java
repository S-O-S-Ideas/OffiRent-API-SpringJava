package com.acme.offirent.resource;

public class OfficeResource {

    private Long id;

    private String address;

    private Long floor;

    private Long capacity;

    private Boolean allowResource;

    private Float score;

    private String description;

    private Float price;

    private Boolean status;

    private String comment;

    public Long getId() {
        return id;
    }

    public OfficeResource setId(Long id) {
        this.id = id;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public OfficeResource setAddress(String address) {
        this.address = address;
        return this;
    }

    public Long getFloor() {
        return floor;
    }

    public OfficeResource setFloor(Long floor) {
        this.floor = floor;
        return this;
    }

    public Long getCapacity() {
        return capacity;
    }

    public OfficeResource setCapacity(Long capacity) {
        this.capacity = capacity;
        return this;
    }

    public Boolean getAllowResource() {
        return allowResource;
    }

    public OfficeResource setAllowResource(Boolean allowResource) {
        this.allowResource = allowResource;
        return this;
    }

    public Float getScore() {
        return score;
    }

    public OfficeResource setScore(Float score) {
        this.score = score;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public OfficeResource setDescription(String description) {
        this.description = description;
        return this;
    }

    public Float getPrice() {
        return price;
    }

    public OfficeResource setPrice(Float price) {
        this.price = price;
        return this;
    }

    public Boolean getStatus() {
        return status;
    }

    public OfficeResource setStatus(Boolean status) {
        this.status = status;
        return this;
    }

    public String getComment() {
        return comment;
    }

    public OfficeResource setComment(String comment) {
        this.comment = comment;
        return this;
    }
}
