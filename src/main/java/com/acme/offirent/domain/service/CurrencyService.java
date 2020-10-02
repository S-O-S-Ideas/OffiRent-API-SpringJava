package com.acme.offirent.domain.service;


import com.acme.offirent.domain.model.Currency;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface CurrencyService {
    Page<Currency> getAllCurrencies(Pageable pageable);
    Page<Currency> getAllCurrenciesByCountryId(Long countryId, Pageable pageable);
    Currency getCurrencyById(Long currencyId);
    Currency createCurrency(Currency currency);
    ResponseEntity<?> deleteCurrency(Long currencyId);


}
