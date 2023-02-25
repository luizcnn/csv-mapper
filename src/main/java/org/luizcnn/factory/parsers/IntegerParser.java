package org.luizcnn.factory.parsers;

import org.luizcnn.factory.ParserFunction;

public class IntegerParser implements ParserFunction<Integer> {
  @Override
  public Integer apply(String value) {
    return Integer.parseInt(value);
  }
}
