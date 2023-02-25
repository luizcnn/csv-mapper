package org.luizcnn.factory.parsers;

import org.luizcnn.factory.ParserFunction;

public class FloatParser implements ParserFunction<Float> {

  private static final FloatParser INSTANCE = new FloatParser();

  private FloatParser() {}

  public static FloatParser getInstance() {
    return INSTANCE;
  }

  @Override
  public Float apply(String value) {
    return Float.parseFloat(value);
  }
}
