package org.luizcnn.factory.parsers;

import org.luizcnn.factory.ParserFunction;

import java.math.BigDecimal;
import java.math.MathContext;

public class BigDecimalParser implements ParserFunction<BigDecimal> {
  @Override
  public BigDecimal apply(String value) {
    return new BigDecimal(value, MathContext.DECIMAL64);
  }
}
