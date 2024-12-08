package org.luizcnn.strategy.serializers;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.luizcnn.strategy.SerializerFunction;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BooleanSerializer implements SerializerFunction<Boolean> {

  private static final BooleanSerializer INSTANCE = new BooleanSerializer();

  public static BooleanSerializer getInstance() {
    return INSTANCE;
  }

  @Override
  public Boolean serialize(String value) {
    return Boolean.valueOf(value);
  }
}
