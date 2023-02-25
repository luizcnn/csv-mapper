package org.luizcnn.factory.parsers;

import org.luizcnn.factory.ParserFunction;

public class DoubleParser implements ParserFunction<Double> {

  private static final DoubleParser INSTANCE = new DoubleParser();

  private DoubleParser() {}

  public static DoubleParser getInstance() {
    return INSTANCE;
  }

  @Override
  public Double apply(String value) {
    return Double.parseDouble(value);
  }
}
