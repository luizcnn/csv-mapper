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
    result.putIfAbsent(String.class, StringParser.getInstance());
    result.putIfAbsent(Integer.class, IntegerParser.getInstance());
    result.putIfAbsent(int.class, IntegerParser.getInstance());
    result.putIfAbsent(UUID.class, UUIDParser.getInstance());
    result.putIfAbsent(Double.class, DoubleParser.getInstance());
    result.putIfAbsent(double.class, DoubleParser.getInstance());
    result.putIfAbsent(BigDecimal.class, BigDecimalParser.getInstance());
    result.putIfAbsent(Boolean.class, BooleanParser.getInstance());
    result.putIfAbsent(boolean.class, BooleanParser.getInstance());
    result.putIfAbsent(Float.class, FloatParser.getInstance());
    result.putIfAbsent(float.class, FloatParser.getInstance());
    return result;
  }

}
