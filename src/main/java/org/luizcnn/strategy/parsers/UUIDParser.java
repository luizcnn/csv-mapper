package org.luizcnn.strategy.parsers;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.luizcnn.strategy.ParserFunction;

import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UUIDParser implements ParserFunction<UUID> {

  private static final UUIDParser INSTANCE = new UUIDParser();

  public static UUIDParser getInstance() {
    return INSTANCE;
  }

  @Override
  public UUID parse(String value) {
    return UUID.fromString(value);
  }
}
