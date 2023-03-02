package org.luizcnn.strategy.parsers;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.luizcnn.strategy.ParserFunction;

import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LocalDateParser implements ParserFunction<LocalDate> {

  private static final LocalDateParser INSTANCE = new LocalDateParser();

  public static LocalDateParser getInstance() {
    return INSTANCE;
  }

  @Override
  public LocalDate parse(String value) {
    return LocalDate.parse(value);
  }
}
