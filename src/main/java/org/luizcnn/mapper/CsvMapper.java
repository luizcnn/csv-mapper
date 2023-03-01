package org.luizcnn.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.luizcnn.annotations.CsvProperty;
import org.luizcnn.exceptions.MissingNoArgsConstructorException;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static org.luizcnn.parser.CsvParser.parseCsvAsListOfMap;
import static org.luizcnn.strategy.ParserFunctionStrategy.getParserFunction;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CsvMapper {

  private static final CsvMapper INSTANCE = new CsvMapper();

  public static CsvMapper getInstance() {
    return INSTANCE;
  }

  public <T> List<T> process(String csv, Class<T> targetClass) {
    List<T> result = new ArrayList<>();

    final var csvAsList = parseCsvAsListOfMap(csv);
    final var csvHeaders = getMapperHelper(targetClass);
    Constructor<T> objectConstructor = getTargetNoArgsConstructor(targetClass);

    csvAsList.forEach(rowAsList -> {
      final var mappedObject = constructInstance(objectConstructor);
      csvHeaders.forEach(helper -> {
        final var csvHeaderAnnotation = (CsvProperty) helper.getAnnotation();
        final var field = helper.getField();
        final var mapKey = isCsvHeaderNameNullOrBlank(csvHeaderAnnotation)
                ? field.getName()
                : csvHeaderAnnotation.name();
        final var value = rowAsList.get(mapKey);
        setFieldValueIntoMappedObject(mappedObject, field, value);
      });
      result.add(mappedObject);
    });

    return result;
  }

  private static <T> List<MapperHelper> getMapperHelper(Class<T> targetClass) {
    return Stream
            .of(targetClass.getDeclaredFields())
            .map(field -> MapperHelper
                    .builder()
                    .field(field)
                    .annotation(field.getAnnotation(CsvProperty.class))
                    .build()
            )
            .filter(helper -> nonNull(helper.getAnnotation()))
            .collect(Collectors.toList());
  }

  private static <T> Constructor<T> getTargetNoArgsConstructor(Class<T> targetClass) {
    try {
      return targetClass.getConstructor();
    } catch (NoSuchMethodException e) {
      throw new MissingNoArgsConstructorException(e);
    }
  }

  private static <T> T constructInstance(Constructor<T> objectConstructor) {
    try {
      return objectConstructor.newInstance();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  private static boolean isCsvHeaderNameNullOrBlank(CsvProperty csvPropertyAnnotation) {
    return isNull(csvPropertyAnnotation.name()) || csvPropertyAnnotation.name().isBlank();
  }

  private static <T> void setFieldValueIntoMappedObject(T mappedObject, Field field, String value) {
    try {
      field.setAccessible(true);
      field.set(mappedObject, getParserFunction(field.getType()).parse(value));
    } catch (IllegalAccessException e) {
      throw new RuntimeException(e);
    }
  }

}
