package com.calculator.currency.domain;

import java.util.List;
import java.util.Map;

public class Countries {
    private final List<Country> countryList;

    public Countries(final List<Country> countryList) {
        this.countryList = countryList;
    }

    public List<Country> getCountryList() {
        return countryList;
    }

    public void updateCurrency(Map<String, Double> currencyList) {
        for (String countryCode : currencyList.keySet()) {
            countryList.stream()
                    .filter(x -> x.getCurrencyCode().equals(countryCode))
                    .findFirst()
                    .ifPresent(country -> country.updateCurrency(currencyList.get(countryCode)));
        }
    }
}
