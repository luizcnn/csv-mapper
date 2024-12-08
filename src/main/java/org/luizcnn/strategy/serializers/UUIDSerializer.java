package org.luizcnn.strategy.serializers;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.luizcnn.strategy.SerializerFunction;

import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UUIDSerializer implements SerializerFunction<UUID> {

  private static final UUIDSerializer INSTANCE = new UUIDSerializer();

  public static UUIDSerializer getInstance() {
    return INSTANCE;
  }

  @Override
  public UUID serialize(String value) {
    return UUID.fromString(value);
  }
}
