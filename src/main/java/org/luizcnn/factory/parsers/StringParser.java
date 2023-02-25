package org.luizcnn.factory.parsers;

import org.luizcnn.factory.ParserFunction;

public class StringParser implements ParserFunction<String> {
  @Override
  public String apply(String value) {
    return value;
  }
}
