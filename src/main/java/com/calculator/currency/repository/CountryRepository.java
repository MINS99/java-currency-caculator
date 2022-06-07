package com.calculator.currency.repository;

import com.calculator.currency.domain.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {
    Country findByCurrencyCode(String currencyCode);
}
