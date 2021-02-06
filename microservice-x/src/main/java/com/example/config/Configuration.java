package com.example.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("microservice-x")
@Data
public class Configuration {

    private String name;
    private String surname;
}
