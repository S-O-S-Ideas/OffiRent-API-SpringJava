package com.acme.offirent.domain.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name="offices")
public class Office {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="district_id", nullable = false)
    @JsonIgnore
    private District district;

    // @ManyToOne(fetch = FetchType.LAZY, optional = false)
    // @JoinColumn(name ="account_id", nullable = false)
    // @JsonIgnore
    // private Account account;

    @OneToMany(mappedBy = "office") //TODO
    private List<Resource> resources;

    public Long getId() {
        return id;
    }

    public Office setId(Long id) {
        this.id = id;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Office setAddress(String address) {
        this.address = address;
        return this;
    }

    public Long getFloor() {
        return floor;
    }

    public Office setFloor(Long floor) {
        this.floor = floor;
        return this;
    }

    public Long getCapacity() {
        return capacity;
    }

    public Office setCapacity(Long capacity) {
        this.capacity = capacity;
        return this;
    }

    public Boolean getAllowResource() {
        return allowResource;
    }

    public Office setAllowResource(Boolean allowResource) {
        this.allowResource = allowResource;
        return this;
    }

    public Float getScore() {
        return score;
    }

    public Office setScore(Float score) {
        this.score = score;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Office setDescription(String description) {
        this.description = description;
        return this;
    }

    public Float getPrice() {
        return price;
    }

    public Office setPrice(Float price) {
        this.price = price;
        return this;
    }

    public Boolean getStatus() {
        return status;
    }

    public Office setStatus(Boolean status) {
        this.status = status;
        return this;
    }

    public String getComment() {
        return comment;
    }

    public Office setComment(String comment) {
        this.comment = comment;
        return this;
    }

    public District getDistrict() {
        return district;
    }

    public Office setDistrict(District district) {
        this.district = district;
        return this;
    }

    // public Account getAccount() {
    //     return account;
    // }

    // public Office setAccount(Account account) {
    //     this.account = account;
    //     return this;
    // }

    public List<Resource> getResources() {
        return resources;
    }

    public Office setResources(List<Resource> resources) {
        this.resources = resources;
        return this;
    }
}
