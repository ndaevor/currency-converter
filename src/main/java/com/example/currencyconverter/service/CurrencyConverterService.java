package com.example.currencyconverter.service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;

import java.util.Map;

@Service
public class CurrencyConverterService {

    @Value("${api.url}")
    private String apiUrl;

    @Value("${api.key}")
    private String apiKey;

    private final RestTemplate restTemplate;

    public CurrencyConverterService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Map<String, Double> getExchangeRates() {
        String url = String.format("%s/latest?access_key=%s", apiUrl, apiKey);

        try {
            // Make the HTTP request to the API
            Map<String, Object> response = restTemplate.getForObject(url, Map.class);

            if (response == null || !response.containsKey("rates")) {
                throw new IllegalArgumentException("Unable to fetch exchange rates.");
            }

            // Extract the rates from the response
            return (Map<String, Double>) response.get("rates");

        } catch (HttpClientErrorException | ResourceAccessException ex) {
            throw new RuntimeException("External API is unavailable. Please try again later.", ex);
        }
    }

    public double convertCurrency(String from, String to, double amount, Map<String, Double> rates) {
        if (!rates.containsKey(to.toUpperCase())) {
            throw new IllegalArgumentException("Currency rate not found for " + to);
        }

        double toRate = rates.get(to.toUpperCase());
        return amount * toRate;
    }
}
