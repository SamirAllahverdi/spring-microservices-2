package com.example.controller;

import com.example.config.Configuration;
import com.example.entity.FullName;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FullNameController {

    private final Configuration configuration;

    public FullNameController(Configuration configuration) {
        this.configuration = configuration;
    }

    @GetMapping("/fullName")
    public FullName getFullName(){
        return new FullName(configuration.getName(),
                            configuration.getSurname());
    }
}
