package com.example.datagen.runner;

import com.example.datagen.model.FenergoRecord;
import com.example.datagen.model.GearsRecord;
import com.example.datagen.util.CsvWriter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.time.LocalDate;
import java.util.*;

@Component
public class DataGenerationRunner implements CommandLineRunner {

    private static final String[] COUNTRIES = {"IN", "SG", "AE", "UK", "DE"};
    private static final String[] RISK = {"LOW", "MEDIUM", "HIGH"};
    private static final String[] ACCOUNT_TYPES = {"SAVINGS", "CURRENT", "BROKERAGE"};

    @Override
    public void run(String... args) throws Exception {

        int recordCount = 1000;
        Random random = new Random();

        File outputDir = new File("output");
        if (!outputDir.exists()) {
            outputDir.mkdirs();
        }

        List<String> fenergoRows = new ArrayList<>();
        List<String> gearsRows = new ArrayList<>();

        for (int i = 0; i < recordCount; i++) {

            String customerId = UUID.randomUUID().toString();
            String fullName = "Customer_" + i;
            String country = COUNTRIES[random.nextInt(COUNTRIES.length)];
            String risk = RISK[random.nextInt(RISK.length)];
            boolean pep = random.nextBoolean();

            FenergoRecord f = new FenergoRecord(
                    customerId,
                    fullName,
                    country,
                    risk,
                    pep
            );

            fenergoRows.add(String.join(",",
                    f.customerId(),
                    f.fullName(),
                    f.countryCode(),
                    f.riskRating(),
                    String.valueOf(f.pepFlag())
            ));

            String accountId = UUID.randomUUID().toString();
            String accountType = ACCOUNT_TYPES[random.nextInt(ACCOUNT_TYPES.length)];
            double balance = Math.round(random.nextDouble() * 1_000_000.0) / 100.0;
            LocalDate opened = LocalDate.now().minusDays(random.nextInt(2000));

            GearsRecord g = new GearsRecord(
                    accountId,
                    customerId,
                    accountType,
                    balance,
                    opened
            );

            gearsRows.add(String.join(",",
                    g.accountId(),
                    g.customerId(),
                    g.accountType(),
                    String.valueOf(g.balance()),
                    g.openedDate().toString()
            ));
        }

        CsvWriter.write(
                "output/Fenergo.csv",
                "customerId,fullName,countryCode,riskRating,pepFlag",
                fenergoRows
        );

        CsvWriter.write(
                "output/gears.csv",
                "accountId,customerId,accountType,balance,openedDate",
                gearsRows
        );

        System.exit(0);
    }
}
