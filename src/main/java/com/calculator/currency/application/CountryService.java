package com.calculator.currency.application;

import com.calculator.currency.dto.CountryResponse;
import com.calculator.currency.repository.CountryRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;

    public List<CountryResponse> findAll() {
        return countryRepository.findAll().stream()
                .map(CountryResponse::of)
                .collect(Collectors.toList());
    }
}
