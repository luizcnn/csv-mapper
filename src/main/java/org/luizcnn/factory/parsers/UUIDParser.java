package org.luizcnn.factory.parsers;

import org.luizcnn.factory.ParserFunction;

import java.util.UUID;

public class UUIDParser implements ParserFunction<UUID> {

  private static final UUIDParser INSTANCE = new UUIDParser();

  private UUIDParser() {}

  public static UUIDParser getInstance() {
    return INSTANCE;
  }

  @Override
  public UUID apply(String value) {
    return UUID.fromString(value);
  }
}
