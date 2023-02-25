package org.luizcnn.strategy.parsers;

import org.luizcnn.strategy.ParserFunction;

public class FloatParser implements ParserFunction<Float> {

  private static final FloatParser INSTANCE = new FloatParser();

  private FloatParser() {}

  public static FloatParser getInstance() {
    return INSTANCE;
  }

  @Override
  public Float parse(String value) {
    return Float.parseFloat(value);
  }
}
