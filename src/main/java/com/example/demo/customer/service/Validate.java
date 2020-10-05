package com.example.demo.customer.service;

import java.util.Optional;
import java.util.UUID;

public class Validate {

    public static final String CUSTOMER_INVALID_ID = "customer.invalid.id";

    
    public static Optional<String> vaidateAndGetErrorMessage(final String customerId) {

        try {
            UUID.fromString(customerId);
            return Optional.empty();
        } catch (Exception e) {
            return Optional.of(CUSTOMER_INVALID_ID);

        }
    }
}
