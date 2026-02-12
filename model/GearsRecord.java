package com.example.datagen.model;

import java.time.LocalDate;

public record GearsRecord(
        String accountId,
        String customerId,
        String accountType,
        double balance,
        LocalDate openedDate
) {}
