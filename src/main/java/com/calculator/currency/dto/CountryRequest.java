package com.calculator.currency.dto;

public class CountryRequest {
    private Long id;
    private String currencyCode;

    public CountryRequest(final String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public Long getId() {
        return id;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }
}
