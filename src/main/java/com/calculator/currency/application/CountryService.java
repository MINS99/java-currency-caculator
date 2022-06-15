package com.calculator.currency.application;

import com.calculator.currency.domain.Countries;
import com.calculator.currency.dto.CountryResponse;
import com.calculator.currency.repository.CountryRepository;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CountryService {

    private CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Transactional(readOnly = true)
    public List<CountryResponse> findAll() {
        return countryRepository.findAll().stream()
                .map(CountryResponse::of)
                .collect(Collectors.toList());
    }

    @Transactional
    public void updateCurrency(Map<String, Double> currencyList) {
        Countries countries = new Countries(countryRepository.findAll());
        countries.updateCurrency(currencyList);
    }
}
