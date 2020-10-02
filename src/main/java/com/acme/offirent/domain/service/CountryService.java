package com.acme.offirent.domain.service;

import com.acme.offirent.domain.model.Country;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface CountryService {
    Country getCountryById(Long countryId);
    Country createCountry(Country country);
    ResponseEntity<?> deleteCountry(Long countryId);
    Country assignCountryCurrency(Long countryId, Long currencyId);
    Country unassignCountryCurrency(Long countryId, Long currencyId);
    Page<Country> getAllCountriesByCurrencyId(Long currencyId, Pageable pageable);

}
