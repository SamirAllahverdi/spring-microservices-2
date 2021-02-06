package com.example.controller;

import com.example.config.Configuration;
import com.example.entity.Limits;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitsController {

    private final Configuration configuration;

    public LimitsController(Configuration configuration) {
        this.configuration = configuration;
    }

    @GetMapping("/limits")
    public Limits getLimits() {
        return new Limits(configuration.getMaximum(),
                configuration.getMinimum());
    }
}
