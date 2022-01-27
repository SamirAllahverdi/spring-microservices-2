package com.example.controller;


import com.example.entity.CurrencyExchange;
import com.example.repository.CurrencyExchangeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class CurrencyExchangeController {

    private final Environment environment;
    private final CurrencyExchangeRepository currencyExchangeRepository;

    /**
     * http://localhost:8000/currency-exchange/from/USD/to/INR
     * http://localhost:8000/currency-exchange/from/EUR/to/INR
     */

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange getExchangeValue(@PathVariable String from,
                                             @PathVariable String to) {
        return currencyExchangeRepository.findByFromAndTo(from, to)
                .map(ce -> {
                    ce.setEnvironment(environment.getProperty("local.server.port"));
                    return ce;
                })
                .orElseThrow(() -> new RuntimeException(String.format("Unable to find data for %s to %s", from, to)));
    }


}
