package org.luizcnn.strategy.parsers;

import org.luizcnn.strategy.ParserFunction;

public class IntegerParser implements ParserFunction<Integer> {

  private static final IntegerParser INSTANCE = new IntegerParser();

  private IntegerParser() {}

  public static IntegerParser getInstance() {
    return INSTANCE;
  }

  @Override
  public Integer parse(String value) {
    return Integer.parseInt(value);
  }
}
