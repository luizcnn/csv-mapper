package org.luizcnn.strategy.serializers;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.luizcnn.strategy.SerializerFunction;

import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LocalDateSerializer implements SerializerFunction<LocalDate> {

  private static final LocalDateSerializer INSTANCE = new LocalDateSerializer();

  public static LocalDateSerializer getInstance() {
    return INSTANCE;
  }

  @Override
  public LocalDate serialize(String value) {
    return LocalDate.parse(value);
  }
}
