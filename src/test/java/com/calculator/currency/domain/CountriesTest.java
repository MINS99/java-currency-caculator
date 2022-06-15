package com.calculator.currency.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CountriesTest {

    Country 한국;
    Country 일본;
    Country 필리핀;

    @BeforeEach
    void setUp() {
        한국 = new Country("한국", "KRW", 1256.35);
        일본 = new Country("일본", "JPY", 132.63);
        필리핀 = new Country("필리핀", "PHP", 52.92);
    }

    @Test
    @DisplayName("환율 데이터를 받아 현재 환율을 갱신한다")
    void updateCurrency() {
        // given
        Map<String, Double> expected = Map.of("KRW", 1288.50, "JPY", 134.50, "PHP", 53.26);

        // when
        Countries countries = new Countries(Arrays.asList(한국, 일본, 필리핀));
        countries.updateCurrency(expected);

        // then
        Map<String, Double> actual = new HashMap<>();
        for (Country country : countries.getCountryList()) {
            actual.put(country.getCurrencyCode(), country.getCurrency());
        }
        assertThat(actual).isEqualTo(expected);
    }
}
