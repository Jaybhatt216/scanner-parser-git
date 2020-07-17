package com.trailblazers.freewheelers.service;

import com.google.common.collect.ImmutableMap;

import java.math.BigDecimal;
import java.util.Map;

public class TaxCalculator {

    // Only UK and Ireland have VAT.
    private static final BigDecimal EXCLUDED_VAT_RATE = new BigDecimal(0);
    // All countries besides France, Germany, Spain have a fixed duty rate of 5%
    private static final BigDecimal EXCLUDED_DUTY_RATE = new BigDecimal(0.05);

    private static final Map<String, BigDecimal> vatRates = ImmutableMap.of(
            "Ireland", new BigDecimal(0.18),
            "United Kingdom", new BigDecimal(0.18)
    );
    private static final Map<String, BigDecimal> dutyRates = ImmutableMap.of(
            "France", new BigDecimal(0.07),
            "Germany", new BigDecimal(0.054),
            "Spain", new BigDecimal(0.09)
    );

    public BigDecimal calculateTaxOnItem(BigDecimal price, String country) {
        return calculateVATOnItem(price, country).add(calculateDutyOnItem(price, country));
    }

    public BigDecimal calculateVATOnItem(BigDecimal price, String country) {
        BigDecimal rate = EXCLUDED_VAT_RATE;    
        if (vatRates.containsKey(country))
            rate = vatRates.get(country);
        return price.multiply(rate).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public BigDecimal calculateDutyOnItem(BigDecimal price, String country) {
        BigDecimal rate = getExcludedDutyRate(country);
        if (dutyRates.containsKey(country))
            rate = dutyRates.get(country);
        return price.multiply(rate).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    private BigDecimal getExcludedDutyRate(String country) {
        if (vatRates.containsKey(country))
            return new BigDecimal(0);
        else
            return EXCLUDED_DUTY_RATE;
    }

    public static String getTaxType(String country) {
        if (vatRates.keySet().contains(country)) {
            return "VAT";
        } else {
            return "Duty";
        }
    }
}
