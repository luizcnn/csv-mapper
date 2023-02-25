package org.luizcnn.strategy.parsers;

import org.luizcnn.strategy.ParserFunction;

public class BooleanParser implements ParserFunction<Boolean> {

  private static final BooleanParser INSTANCE = new BooleanParser();

  private BooleanParser() {}

  public static BooleanParser getInstance() {
    return INSTANCE;
  }

  @Override
  public Boolean parse(String value) {
    return Boolean.valueOf(value);
  }
}
