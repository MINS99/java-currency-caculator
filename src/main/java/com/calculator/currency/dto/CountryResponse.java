package com.calculator.currency.dto;

import com.calculator.currency.domain.Country;

public class CountryResponse {
    private final Long id;
    private final String countryName;
    private final String currencyCode;
    private final double currency;

    public static CountryResponse of(final Country country) {
        return new CountryResponse(country.getId(), country.getCountryName(), country.getCurrencyCode(), country.getCurrency());
    }

    private CountryResponse(final Long id, final String countryName, final String currencyCode, final double currency) {
        this.id = id;
        this.countryName = countryName;
        this.currencyCode = currencyCode;
        this.currency = currency;
    }

    public Long getId() {
        return id;
    }

    public String getCountryName() {
        return countryName;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public double getCurrency() {
        return currency;
    }
}
