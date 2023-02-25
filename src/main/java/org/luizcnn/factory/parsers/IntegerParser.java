package org.luizcnn.factory.parsers;

import org.luizcnn.factory.ParserFunction;

public class IntegerParser implements ParserFunction<Integer> {

  private static final IntegerParser INSTANCE = new IntegerParser();

  private IntegerParser() {}

  public static IntegerParser getInstance() {
    return INSTANCE;
  }

  @Override
  public Integer apply(String value) {
    return Integer.parseInt(value);
  }
}
