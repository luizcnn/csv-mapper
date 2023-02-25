package org.luizcnn.factory.parsers;

import org.luizcnn.factory.ParserFunction;

public class FloatParser implements ParserFunction<Float> {
  @Override
  public Float apply(String value) {
    return Float.parseFloat(value);
  }
}
