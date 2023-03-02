package org.luizcnn.strategy;

import org.luizcnn.annotations.CsvPropertyParser;
import org.luizcnn.strategy.factory.DefaultParserFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import static java.util.Objects.nonNull;
import static java.util.Optional.ofNullable;

public class ParserFunctionStrategy {

  public static ParserFunction<?> getParserFunction(Field field) {
    final var parser = ofNullable(field.getDeclaredAnnotation(CsvPropertyParser.class))
            .map(CsvPropertyParser::using)
            .orElse(null);
    if (nonNull(parser)) {
      try {
        final var customParserConstructor = parser.getDeclaredConstructor();
        customParserConstructor.setAccessible(true);
        return customParserConstructor.newInstance();
      } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
        throw new RuntimeException(e);
      }
    }
    return DefaultParserFactory.getByFieldType(field.getType());
  }

}
