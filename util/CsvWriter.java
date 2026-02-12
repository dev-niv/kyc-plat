package com.example.datagen.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CsvWriter {

    public static void write(String filePath, String header, List<String> rows) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(header);
            writer.newLine();
            for (String row : rows) {
                writer.write(row);
                writer.newLine();
            }
        }
    }
}
