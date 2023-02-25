package org.luizcnn.factory.parsers;

import org.luizcnn.factory.ParserFunction;

public class BooleanParser implements ParserFunction<Boolean> {
  @Override
  public Boolean apply(String value) {
    return Boolean.valueOf(value);
  }
}
