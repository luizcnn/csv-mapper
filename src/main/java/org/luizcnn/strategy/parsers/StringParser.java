package org.luizcnn.strategy.parsers;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.luizcnn.strategy.ParserFunction;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StringParser implements ParserFunction<String> {

  private static final StringParser INSTANCE = new StringParser();

  public static StringParser getInstance() {
    return INSTANCE;
  }

  @Override
  public String parse(String value) {
    return value;
  }
}
