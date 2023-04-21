package com.vamshi.currencyexchangemicroservice;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CircuitBreakerController {

    Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);

    @GetMapping("/demo-api")
    @CircuitBreaker(name = "demo-api", fallbackMethod = "hardCodedMethod")
    public String demoApi() {
        logger.info("Api call received");
        return new RestTemplate().getForObject("localhost:9092/get", String.class);
    }

    public String hardCodedMethod() {
        return "Fallback-Response";
    }
}
