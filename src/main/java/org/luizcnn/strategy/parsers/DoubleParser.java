package org.luizcnn.strategy.parsers;

import org.luizcnn.strategy.ParserFunction;

public class DoubleParser implements ParserFunction<Double> {

  private static final DoubleParser INSTANCE = new DoubleParser();

  private DoubleParser() {}

  public static DoubleParser getInstance() {
    return INSTANCE;
  }

  @Override
  public Double parse(String value) {
    return Double.parseDouble(value);
  }
}
