package org.luizcnn.strategy.serializers;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.luizcnn.strategy.SerializerFunction;

import java.math.BigDecimal;
import java.math.MathContext;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BigDecimalSerializer implements SerializerFunction<BigDecimal> {

  private static final BigDecimalSerializer INSTANCE = new BigDecimalSerializer();

  public static BigDecimalSerializer getInstance() {
    return INSTANCE;
  }

  @Override
  public BigDecimal serialize(String value) {
    return new BigDecimal(value, MathContext.DECIMAL64);
  }
}
