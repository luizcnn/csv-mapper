package org.luizcnn.strategy.parsers;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.luizcnn.strategy.ParserFunction;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LocalDateTimeParser implements ParserFunction<LocalDateTime> {

  private static final LocalDateTimeParser INSTANCE = new LocalDateTimeParser();

  public static LocalDateTimeParser getInstance() {
    return INSTANCE;
  }

  @Override
  public LocalDateTime parse(String value) {
    return LocalDateTime.parse(value);
  }
}
