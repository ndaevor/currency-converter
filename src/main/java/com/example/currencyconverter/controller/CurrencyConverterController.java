package com.example.currencyconverter.controller;

import com.example.currencyconverter.model.CurrencyConversionRequest;
import com.example.currencyconverter.service.CurrencyConverterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/currency")
public class CurrencyConverterController {

    @Autowired
    private CurrencyConverterService currencyConverterService;

    // Convert currency - POST method
    @PostMapping("/convert")
    public Map<String, Object> convertCurrency(@RequestBody CurrencyConversionRequest request) {
        // Fetch the exchange rates for the base currency
        Map<String, Double> rates = currencyConverterService.getExchangeRates();

        // Convert the amount
        double convertedAmount = currencyConverterService.convertCurrency(
                request.getFrom(),
                request.getTo(),
                request.getAmount(),
                rates
        );

        return Map.of(
                "from", request.getFrom(),
                "to", request.getTo(),
                "amount", request.getAmount(),
                "convertedAmount", convertedAmount
        );
    }

    // Get exchange rates - GET method
    @GetMapping("/rates")
    public Map<String, Double> getExchangeRates(@RequestParam(defaultValue = "USD") String base) {
        return currencyConverterService.getExchangeRates();
    }
}
