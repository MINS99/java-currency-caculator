package com.calculator.currency.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String countryName;
    private String currencyCode;
    private double currency;

    protected Country() {

    }

    public Country(String countryName, String currencyCode, double currency) {
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
