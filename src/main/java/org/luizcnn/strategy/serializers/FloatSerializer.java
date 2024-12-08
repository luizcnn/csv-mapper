package org.luizcnn.strategy.serializers;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.luizcnn.strategy.SerializerFunction;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FloatSerializer implements SerializerFunction<Float> {

  private static final FloatSerializer INSTANCE = new FloatSerializer();

  public static FloatSerializer getInstance() {
    return INSTANCE;
  }

  @Override
  public Float serialize(String value) {
    return Float.parseFloat(value);
  }
}
