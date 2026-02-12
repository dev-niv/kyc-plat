package com.example.datagen.model;

public record FenergoRecord(
        String customerId,
        String fullName,
        String countryCode,
        String riskRating,
        boolean pepFlag
) {}
