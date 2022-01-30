package com.example.controller;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class Resilience4jTestController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping("/sample-api-retry")
    @Retry(name = "sample-api-retry", fallbackMethod = "hardcodedResponse") // default is 3
    public String sampleApiRetry() {
        logger.info("Sample api retry called...");
        ResponseEntity<String> responseEntity = new RestTemplate().getForEntity("http://localhost:8761/dummy-url", String.class);
        return responseEntity.getBody();
    }

    @GetMapping("/sample-api-circuit-breaker")
    @CircuitBreaker(name = "default", fallbackMethod = "hardcodedResponse")
    public String sampleApiCircuitBreaker() {
        logger.info("Sample api circuit breaker called...");
        ResponseEntity<String> responseEntity = new RestTemplate().getForEntity("http://localhost:8761/dummy-url", String.class);
        return responseEntity.getBody();
    }

    @GetMapping("/sample-api-rate-limiter")
    @RateLimiter(name = "default")
    public String sampleApiRateLimiter() {
        logger.info("Sample api rate limiting called...");
        return "Sample Api Rate Limiting";
    }

    @GetMapping("/sample-api-bulk-head")
    @Bulkhead(name = "default")
    public String sampleApiBulkHead() {
        logger.info("Sample api bulk head called...");
        return "Sample Api Bulk Head";
    }


    public String hardcodedResponse(Exception ex) {
        return "fallback-response";
    }
}
