package org.luizcnn.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.luizcnn.annotations.CsvHeader;
import org.luizcnn.exceptions.MissingNoArgsConstructorException;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Objects.nonNull;
import static org.luizcnn.strategy.ParserFunctionStrategy.getParserFunction;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CsvMapper {

  private static final CsvMapper INSTANCE = new CsvMapper();

  public static CsvMapper getInstance() {
    return INSTANCE;
  }

  public <T> List<T> process(String csv, Class<T> targetClass) {
    List<T> result = new ArrayList<>();

    final var csvAsList = getCsvAsList(csv);
    final var csvHeaders = getCsvHeaders(targetClass);
    Constructor<T> objectConstructor = getTargetNoArgsConstructor(targetClass);

    validateHeadersPositions(csvAsList, csvHeaders);

    csvAsList.forEach(rowAsList -> {
      final var mappedObject = constructInstance(objectConstructor);
      csvHeaders.forEach(helper -> {
        final var csvHeaderAnnotation = (CsvHeader) helper.getAnnotation();
        final var field = helper.getField();
        int position = csvHeaderAnnotation.position();
        final var value = rowAsList.get(position);
        setFieldValueIntoMappedObject(mappedObject, field, value);
      });
      result.add(mappedObject);
    });

    return result;
  }

  private static List<List<String>> getCsvAsList(String csv) {
    return Arrays.stream(csv.trim().split("\n"))
            .map(row -> Arrays.asList(row.split(",")))
            .collect(Collectors.toList());
  }

  private static <T> List<MapperHelper> getCsvHeaders(Class<T> targetClass) {
    return Stream
            .of(targetClass.getDeclaredFields())
            .map(field -> MapperHelper
                    .builder()
                    .field(field)
                    .annotation(field.getAnnotation(CsvHeader.class))
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

  /*
    This validation will be removed when header position do not be no longer important
   */
  @Deprecated
  private static void validateHeadersPositions(List<List<String>> csvAsList, List<MapperHelper> headers) {
    final var validator = headers
            .stream()
            .map(helper -> (CsvHeader) helper.getAnnotation())
            .map(CsvHeader::name)
            .collect(Collectors.toList());

    final var csvHeaders = csvAsList.get(0);
    for(int i = 0; i < csvHeaders.size(); i++) {
      if (!csvHeaders.get(i).equals(validator.get(i))) {
        throw new RuntimeException("CSV file headers does not correspond to Schema. CsvSchemaHeaders:" + headers);
      }
    }
    csvAsList.remove(0); //removing header after pass successfully through validation
  }

  private static <T> T constructInstance(Constructor<T> objectConstructor) {
    try {
      return objectConstructor.newInstance();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
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
