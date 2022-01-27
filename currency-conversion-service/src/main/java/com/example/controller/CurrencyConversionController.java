package com.example.controller;

import com.example.entity.CurrencyConversion;
import com.example.proxy.CurrencyExchangeProxy;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
public class CurrencyConversionController {

    private final CurrencyExchangeProxy currencyExchangeProxy;

    /**
     * http://localhost:8100/currency-conversion/from/USD/to/INR/quantity/10
     */
    @GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversion(@PathVariable String from,
                                                          @PathVariable String to,
                                                          @PathVariable BigDecimal quantity) {
        CurrencyConversion currencyConversion = currencyExchangeProxy.getExchangeValue(from, to);

        return CurrencyConversion.builder()
                .id(currencyConversion.getId())
                .from(from).to(to)
                .quantity(quantity)
                .conversionMultiple(quantity.multiply(currencyConversion.getConversionMultiple()))
                .build();
    }
}
