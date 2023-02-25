package org.luizcnn.factory.parsers;

import org.luizcnn.factory.ParserFunction;

import java.util.UUID;

public class UUIDParser implements ParserFunction<UUID> {
  @Override
  public UUID apply(String value) {
    return UUID.fromString(value);
  }
}
