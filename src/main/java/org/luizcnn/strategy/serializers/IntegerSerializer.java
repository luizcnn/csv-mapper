package org.luizcnn.strategy.serializers;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.luizcnn.strategy.SerializerFunction;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class IntegerSerializer implements SerializerFunction<Integer> {

  private static final IntegerSerializer INSTANCE = new IntegerSerializer();

  public static IntegerSerializer getInstance() {
    return INSTANCE;
  }

  @Override
  public Integer serialize(String value) {
    return Integer.parseInt(value);
  }
}
