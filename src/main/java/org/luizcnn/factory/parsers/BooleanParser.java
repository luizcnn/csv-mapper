package org.luizcnn.factory.parsers;

import org.luizcnn.factory.ParserFunction;

public class BooleanParser implements ParserFunction<Boolean> {

  private static final BooleanParser INSTANCE = new BooleanParser();

  private BooleanParser() {}

  public static BooleanParser getInstance() {
    return INSTANCE;
  }

  @Override
  public Boolean apply(String value) {
    return Boolean.valueOf(value);
  }
}
