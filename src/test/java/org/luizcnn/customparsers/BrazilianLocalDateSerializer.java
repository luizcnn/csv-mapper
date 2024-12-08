package org.luizcnn.customparsers;

import org.luizcnn.strategy.SerializerFunction;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class BrazilianLocalDateSerializer implements SerializerFunction<LocalDate> {

  @Override
  public LocalDate serialize(String value) {
    return LocalDate.parse(value, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
  }

}
