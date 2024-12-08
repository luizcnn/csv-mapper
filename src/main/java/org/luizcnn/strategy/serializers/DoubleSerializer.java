package org.luizcnn.strategy.serializers;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.luizcnn.strategy.SerializerFunction;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DoubleSerializer implements SerializerFunction<Double> {

  private static final DoubleSerializer INSTANCE = new DoubleSerializer();

  public static DoubleSerializer getInstance() {
    return INSTANCE;
  }

  @Override
  public Double serialize(String value) {
    return Double.parseDouble(value);
  }
}
