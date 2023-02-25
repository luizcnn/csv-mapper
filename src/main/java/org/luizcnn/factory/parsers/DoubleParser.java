package org.luizcnn.factory.parsers;

import org.luizcnn.factory.ParserFunction;

public class DoubleParser implements ParserFunction<Double> {
  @Override
  public Double apply(String value) {
    return Double.parseDouble(value);
  }
}
