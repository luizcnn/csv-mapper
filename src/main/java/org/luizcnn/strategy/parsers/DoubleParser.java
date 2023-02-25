package org.luizcnn.strategy.parsers;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.luizcnn.strategy.ParserFunction;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DoubleParser implements ParserFunction<Double> {

  private static final DoubleParser INSTANCE = new DoubleParser();

  public static DoubleParser getInstance() {
    return INSTANCE;
  }

  @Override
  public Double parse(String value) {
    return Double.parseDouble(value);
  }
}
