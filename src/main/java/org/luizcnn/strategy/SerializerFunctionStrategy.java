package org.luizcnn.strategy;

import org.luizcnn.annotations.CsvPropertySerializer;
import org.luizcnn.strategy.factory.DefaultParserFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import static java.util.Objects.nonNull;
import static java.util.Optional.ofNullable;

public class SerializerFunctionStrategy {

  public static SerializerFunction<?> getSerializerFunction(Field field) {
    final var parser = ofNullable(field.getDeclaredAnnotation(CsvPropertySerializer.class))
            .map(CsvPropertySerializer::using)
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
