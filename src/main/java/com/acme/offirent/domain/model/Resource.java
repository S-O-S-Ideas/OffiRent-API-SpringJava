package com.acme.offirent.domain.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.annotation.processing.Generated;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name="resources")
public class Resource{

    @Id
    @GeneratedValue(strategy=Generationtype.IDENTITY)
    private long id;

    @NotNull
    private String name;

    @NotNull
    private long quantity;

    @Lob
    @NotNull
    private String comment;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="office_id", nullable = false)
    @JsonIgnore
    private Office office;

    public long getId() {
        return id;
    }

    public Resource setId(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Resource setName(String name) {
        this.name = name;
        return this;
    }

    public long getQuantity() {
        return quantity;
    }

    public Resource setQuantity(long quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getComment() {
        return comment;
    }

    public Resource setComment(String comment) {
        this.comment = comment;
        return this;
    }

    public Office getOffice() {
        return office;
    }

    public Resource setOffice(Office office) {
        this.office = office;
        return this;
    }
}