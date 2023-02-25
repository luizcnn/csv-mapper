package org.luizcnn.strategy.parsers;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.luizcnn.strategy.ParserFunction;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BooleanParser implements ParserFunction<Boolean> {

  private static final BooleanParser INSTANCE = new BooleanParser();

  public static BooleanParser getInstance() {
    return INSTANCE;
  }

  @Override
  public Boolean parse(String value) {
    return Boolean.valueOf(value);
  }
}
