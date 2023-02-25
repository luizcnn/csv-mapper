package org.luizcnn.strategy.parsers;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.luizcnn.strategy.ParserFunction;

import java.math.BigDecimal;
import java.math.MathContext;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BigDecimalParser implements ParserFunction<BigDecimal> {

  private static final BigDecimalParser INSTANCE = new BigDecimalParser();

  public static BigDecimalParser getInstance() {
    return INSTANCE;
  }

  @Override
  public BigDecimal parse(String value) {
    return new BigDecimal(value, MathContext.DECIMAL64);
  }
}
