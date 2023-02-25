package org.luizcnn.strategy.parsers;

import org.luizcnn.strategy.ParserFunction;

import java.util.UUID;

public class UUIDParser implements ParserFunction<UUID> {

  private static final UUIDParser INSTANCE = new UUIDParser();

  private UUIDParser() {}

  public static UUIDParser getInstance() {
    return INSTANCE;
  }

  @Override
  public UUID parse(String value) {
    return UUID.fromString(value);
  }
}
