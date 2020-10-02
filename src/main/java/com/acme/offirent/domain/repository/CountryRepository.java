package com.acme.offirent.domain.repository;

import com.acme.offirent.domain.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface CountryRepository extends JpaRepository<Country, Long> {

    public Optional<Country> findByName(String name);
}
