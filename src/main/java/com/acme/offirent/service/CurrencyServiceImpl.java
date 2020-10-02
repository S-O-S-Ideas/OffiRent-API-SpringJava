package com.acme.offirent.service;

import com.acme.offirent.domain.model.Currency;
import com.acme.offirent.domain.repository.CountryRepository;
import com.acme.offirent.domain.repository.CurrencyRepository;
import com.acme.offirent.domain.service.CurrencyService;
import com.acme.offirent.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class CurrencyServiceImpl implements CurrencyService {
    @Autowired
    private CurrencyRepository currencyRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Override
    public Page<Currency> getAllCurrencies(Pageable pageable) {
        return currencyRepository.findAll(pageable);
    }

    @Override
    public Page<Currency> getAllCurrenciesByCountryId(Long countryId, Pageable pageable) {
        return countryRepository.findById(countryId)
                .map(country -> {
                    List<Currency> currencies = country.getCurrencies();
                    int currenciesCount = currencies.size();
                    return new PageImpl<>(currencies,pageable,currenciesCount);
                }).orElseThrow(()->new ResourceNotFoundException("Country","Id",countryId));
    }

    @Override
    public Currency getCurrencyById(Long currencyId) {
        return currencyRepository.findById(currencyId)
                .orElseThrow(()->new ResourceNotFoundException("Currency","Id",currencyId));
    }

    @Override
    public Currency createCurrency(Currency currency) {
        return currencyRepository.save(currency);
    }

    @Override
    public ResponseEntity<?> deleteCurrency(Long currencyId) {
        return currencyRepository.findById(currencyId)
                .map(currency -> {currencyRepository.delete(currency);
                return ResponseEntity.ok().build();
                }).orElseThrow(()->new ResourceNotFoundException("Currency","Id",currencyId));
    }
}
