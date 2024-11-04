package com.data.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jsonSchema.jakarta.JsonSchema;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import java.util.ArrayList;
import java.util.List;

import com.data.example.model.Iris;
import com.data.example.schema.ExtendedValidationSchemaFactoryWrapper;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        List<Iris> irisList = new ArrayList<Iris>();
        int max = 20;
        for (int i = 0; i < max; i++) {
            Iris irisInstance = new Iris(
                    i, i + 1, i + 2, i + 3, i + 4);
            irisList.add(irisInstance);
        }

        printJson(irisList);
        printCsv(irisList);
    }

    protected static void printJson(List<Iris> irisList) {
        ObjectMapper mapper = new ObjectMapper();
        ExtendedValidationSchemaFactoryWrapper schema = new ExtendedValidationSchemaFactoryWrapper();

        try {
            mapper.acceptJsonFormatVisitor(Iris.class, schema);
            JsonSchema jsonSchema = schema.finalSchema();
            String schemaValue = mapper
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(jsonSchema);
            System.out.println("JSON");
            System.out.println(schemaValue);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    protected static void printCsv(List<Iris> irisList) {
        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = mapper.schemaFor(Iris.class)
                .withHeader()
                .withQuoteChar('\"')
                .withColumnSeparator(',');

        try {
            String csvValues = mapper.writer().with(schema).writeValueAsString(irisList);
            System.out.println("CSV");
            System.out.println(csvValues);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
