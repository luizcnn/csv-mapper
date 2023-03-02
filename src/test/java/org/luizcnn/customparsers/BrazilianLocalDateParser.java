package org.luizcnn.customparsers;

import org.luizcnn.strategy.ParserFunction;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class BrazilianLocalDateParser implements ParserFunction<LocalDate> {

  @Override
  public LocalDate parse(String value) {
    return LocalDate.parse(value, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
  }

}
