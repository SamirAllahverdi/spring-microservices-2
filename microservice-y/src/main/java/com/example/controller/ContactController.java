package com.example.controller;

import com.example.config.Configuration;
import com.example.entity.Contact;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContactController {
    private final Configuration configuration;


    public ContactController(Configuration configuration) {
        this.configuration = configuration;
    }

    @GetMapping("/contact")
    public Contact getContact(){
        return new Contact(configuration.getEmail(),configuration.getPhone());
    }
}
