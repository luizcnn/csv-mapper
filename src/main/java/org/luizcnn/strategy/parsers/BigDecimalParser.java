package org.luizcnn.strategy.parsers;

import org.luizcnn.strategy.ParserFunction;

import java.math.BigDecimal;
import java.math.MathContext;

public class BigDecimalParser implements ParserFunction<BigDecimal> {

  private static final BigDecimalParser INSTANCE = new BigDecimalParser();

  private BigDecimalParser() {}

  public static BigDecimalParser getInstance() {
    return INSTANCE;
  }

  @Override
  public BigDecimal parse(String value) {
    return new BigDecimal(value, MathContext.DECIMAL64);
  }
}
