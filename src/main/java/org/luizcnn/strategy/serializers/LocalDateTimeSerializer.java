package org.luizcnn.strategy.serializers;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.luizcnn.strategy.SerializerFunction;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LocalDateTimeSerializer implements SerializerFunction<LocalDateTime> {

  private static final LocalDateTimeSerializer INSTANCE = new LocalDateTimeSerializer();

  public static LocalDateTimeSerializer getInstance() {
    return INSTANCE;
  }

  @Override
  public LocalDateTime serialize(String value) {
    return LocalDateTime.parse(value);
  }
}
