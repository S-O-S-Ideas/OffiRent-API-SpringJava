package com.acme.offirent.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name="districts")
public class District {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 100)
    private String name;

    @OneToMany(mappedBy = "district")
    private List<Office> offices;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="department_id", nullable = false)
    @JsonIgnore
    private Department department;

    public Long getId() {
        return id;
    }

    public District setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public District setName(String name) {
        this.name = name;
        return this;
    }

    public List<Office> getOffices() {
        return offices;
    }

    public District setOffices(List<Office> offices) {
        this.offices = offices;
        return this;
    }

    public Department getDepartament() {
        return department;
    }

    public District setDepartament(Department departament) {
        this.department = departament;
        return this;
    }
}
