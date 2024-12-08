package org.luizcnn.customparsers;

import org.luizcnn.strategy.DeserializerFunction;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class BrazilianLocalDateDeserializer implements DeserializerFunction<LocalDate> {

    @Override
    public String deserialize(LocalDate value) {
        final var dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return dateTimeFormatter.format(value);
    }

}
