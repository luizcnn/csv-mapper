package org.luizcnn.strategy.serializers;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.luizcnn.strategy.SerializerFunction;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StringSerializer implements SerializerFunction<String> {

  private static final StringSerializer INSTANCE = new StringSerializer();

  public static StringSerializer getInstance() {
    return INSTANCE;
  }

  @Override
  public String serialize(String value) {
    return value;
  }
}
