package org.luizcnn.strategy;

import org.luizcnn.annotations.CsvPropertyDeserializer;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import static java.util.Objects.nonNull;
import static java.util.Optional.ofNullable;

public class DeserializerFunctionStrategy {

  public static DeserializerFunction<?> getDeserializerFunction(Field field) {
    final var parser = ofNullable(field.getDeclaredAnnotation(CsvPropertyDeserializer.class))
            .map(CsvPropertyDeserializer::using)
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
    return new DeserializerFunction<>() {
        @Override
        public String deserialize(Object value) {
            return DeserializerFunction.super.deserialize(value);
        }
    };
  }

}
