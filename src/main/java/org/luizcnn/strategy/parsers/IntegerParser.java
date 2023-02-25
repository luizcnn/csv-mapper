package org.luizcnn.strategy.parsers;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.luizcnn.strategy.ParserFunction;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class IntegerParser implements ParserFunction<Integer> {

  private static final IntegerParser INSTANCE = new IntegerParser();

  public static IntegerParser getInstance() {
    return INSTANCE;
  }

  @Override
  public Integer parse(String value) {
    return Integer.parseInt(value);
  }
}
