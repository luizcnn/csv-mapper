package org.luizcnn.factory;

import org.luizcnn.factory.parsers.BigDecimalParser;
import org.luizcnn.factory.parsers.BooleanParser;
import org.luizcnn.factory.parsers.DoubleParser;
import org.luizcnn.factory.parsers.FloatParser;
import org.luizcnn.factory.parsers.IntegerParser;
import org.luizcnn.factory.parsers.StringParser;
import org.luizcnn.factory.parsers.UUIDParser;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static java.util.Optional.ofNullable;

public class ParserFunctionFactory {

  //TODO apply Strategy Pattern associate with singletons

  private static final Map<Class<?>, ParserFunction<?>> PARSER_MAPPER = buildParserMapper();

  public static ParserFunction<?> getParserFunction(Class<?> target) {
    return ofNullable(PARSER_MAPPER.get(target))
            .orElseThrow(() -> new IllegalArgumentException(""));
  }

  private static Map<Class<?>, ParserFunction<?>> buildParserMapper() {
    final var result = new HashMap<Class<?>, ParserFunction<?>>();
    result.putIfAbsent(String.class, new StringParser());
    result.putIfAbsent(Integer.class, new IntegerParser());
    result.putIfAbsent(int.class, new IntegerParser());
    result.putIfAbsent(UUID.class, new UUIDParser());
    result.putIfAbsent(Double.class, new DoubleParser());
    result.putIfAbsent(double.class, new DoubleParser());
    result.putIfAbsent(BigDecimal.class, new BigDecimalParser());
    result.putIfAbsent(Boolean.class, new BooleanParser());
    result.putIfAbsent(boolean.class, new BooleanParser());
    result.putIfAbsent(Float.class, new FloatParser());
    result.putIfAbsent(float.class, new FloatParser());
    return result;
  }

}
