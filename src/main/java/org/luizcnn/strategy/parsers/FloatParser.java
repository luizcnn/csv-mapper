package org.luizcnn.strategy.parsers;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.luizcnn.strategy.ParserFunction;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FloatParser implements ParserFunction<Float> {

  private static final FloatParser INSTANCE = new FloatParser();

  public static FloatParser getInstance() {
    return INSTANCE;
  }

  @Override
  public Float parse(String value) {
    return Float.parseFloat(value);
  }
}
