package org.luizcnn.strategy.parsers;

import org.luizcnn.strategy.ParserFunction;

public class StringParser implements ParserFunction<String> {

  private static final StringParser INSTANCE = new StringParser();

  private StringParser() {}

  public static StringParser getInstance() {
    return INSTANCE;
  }

  @Override
  public String parse(String value) {
    return value;
  }
}
