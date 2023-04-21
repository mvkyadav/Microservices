package com.vamshi.currencyexchangemicroservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {

    Logger logger = LoggerFactory.getLogger(CurrencyExchangeController.class);
    @Autowired
    Environment environment;

    @Autowired
    CurrencyExchangeRepository repository;

    @GetMapping("/get")
    public String hello() {
        return "hello";
    }

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange
    currencyExchange(@PathVariable("from") String from, @PathVariable("to") String to) {

        CurrencyExchange record = repository.getByFromAndTo(from, to);
        logger.info("retrievedExchangeValue called with {} to {}", from, to);

        if (record == null) {
            throw new RuntimeException("Unable to find data from " + from + " to " + to);
        }
        record.setInstance(environment.getProperty("local.server.port"));
        return record;
    }
}
