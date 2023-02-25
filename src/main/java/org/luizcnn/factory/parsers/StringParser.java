package org.luizcnn.factory.parsers;

import org.luizcnn.factory.ParserFunction;

public class StringParser implements ParserFunction<String> {

  private static final StringParser INSTANCE = new StringParser();

  private StringParser() {}

  public static StringParser getInstance() {
    return INSTANCE;
  }

  @Override
  public String apply(String value) {
    return value;
  }
}
