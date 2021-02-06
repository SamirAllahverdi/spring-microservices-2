package com.example.controller;


import com.example.entity.CurrencyExchange;
import com.example.repository.CurrencyExchangeRepository;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class CurrencyExchangeController {


    private final Environment environment;

    private final CurrencyExchangeRepository currencyExchangeRepository;

    public CurrencyExchangeController(Environment environment, CurrencyExchangeRepository currencyExchangeRepository) {
        this.environment = environment;
        this.currencyExchangeRepository = currencyExchangeRepository;
    }

    /**
     * http://localhost:8000/currency-exchange/from/USD/to/INR
     * http://localhost:8000/currency-exchange/from/EUR/to/INR
     */

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange getExchangeValue(@PathVariable String from,
                                             @PathVariable String to) {
//        CurrencyExchange currencyExchange = new CurrencyExchange(1001L, from, to, BigDecimal.valueOf(50));
        Optional<CurrencyExchange> currencyExchangeOpt = currencyExchangeRepository.findByFromAndTo(from, to);

        if (currencyExchangeOpt.isEmpty()) {
            throw new RuntimeException("Unable to find data for " + from + " to " + to);
        }else {
            CurrencyExchange currencyExchange = currencyExchangeOpt.get();
            currencyExchange.setEnvironment(environment.getProperty("local.server.port"));

            return currencyExchange;
        }
    }


}
