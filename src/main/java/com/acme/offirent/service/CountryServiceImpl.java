package com.acme.offirent.service;

import com.acme.offirent.domain.model.Country;
import com.acme.offirent.domain.model.Currency;
import com.acme.offirent.domain.repository.CountryRepository;
import com.acme.offirent.domain.repository.CurrencyRepository;
import com.acme.offirent.domain.service.CountryService;
import com.acme.offirent.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private CurrencyRepository currencyRepository;

    @Override
    public Country getCountryById(Long countryId) {
       return countryRepository.findById(countryId)
               .orElseThrow(()->
                       new ResourceNotFoundException("Country","Id",countryId));
    }
    @Override
    public Country createCountry(Country country){
        return countryRepository.save(country);
    }

    @Override
    public ResponseEntity<?> deleteCountry(Long countryId) {
       Country country = countryRepository.findById(countryId)
               .orElseThrow(()->new ResourceNotFoundException("Country","Id",countryId));
       countryRepository.delete(country);
       return ResponseEntity.ok().build();
    }

    @Override
    public Country assignCountryCurrency(Long countryId, Long currencyId) {
        Currency currency = currencyRepository.findById(currencyId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Currency", "Id", currencyId));
        return countryRepository.findById(countryId).map(
                country -> countryRepository.save(country.currencyWith(currency)))
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Country", "Id", countryId));

    }

    @Override
    public Country unassignCountryCurrency(Long countryId, Long currencyId) {
        Currency currency = currencyRepository.findById(currencyId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Currency", "Id", currencyId));
        return countryRepository.findById(countryId).map(
                country -> countryRepository.save(country.unCurrencyWith(currency)))
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Country", "Id", countryId));

    }

    @Override
    public Page<Country> getAllCountriesByCurrencyId(Long currencyId, Pageable pageable) {
        return currencyRepository.findById(currencyId)
                .map(currency->{
                    List<Country> countries = currency.getCountries();
                    int countriesCount = countries.size();
                    return new PageImpl<>(countries, pageable, countriesCount);
                }).orElseThrow(()->new ResourceNotFoundException("Currency","Id",currencyId));
    }
}
