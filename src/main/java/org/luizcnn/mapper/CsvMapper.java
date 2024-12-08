package org.luizcnn.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.luizcnn.annotations.CsvProperty;
import org.luizcnn.exceptions.MissingNoArgsConstructorException;
import org.luizcnn.strategy.DeserializerFunction;

import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static org.luizcnn.csvparser.CsvParser.parseCsvAsListOfMap;
import static org.luizcnn.strategy.SerializerFunctionStrategy.getSerializerFunction;
import static org.luizcnn.strategy.DeserializerFunctionStrategy.getDeserializerFunction;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CsvMapper {

  private static final CsvMapper INSTANCE = new CsvMapper();

  public static CsvMapper getInstance() {
    return INSTANCE;
  }

  public <T> List<T> fromCSV(String csv, Class<T> targetClass) {
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

  public <T> String writeValueAsString(Collection<T> value) {
    if (nonNull(value) && !value.isEmpty()) {
      final var mapperHelper = getMapperHelper(value.stream().findAny().get().getClass());
      StringWriter sw = new StringWriter();
      final var csvFormat = CSVFormat.DEFAULT
              .builder()
              .setHeader(mapperHelper.stream().map(it -> {
                final var csvHeaderAnnotation = (CsvProperty) it.getAnnotation();
                final var field = it.getField();
                return isCsvHeaderNameNullOrBlank(csvHeaderAnnotation)
                        ? field.getName()
                        : csvHeaderAnnotation.name();
              }).toArray(String[]::new))
              .build();

      try (final CSVPrinter printer = new CSVPrinter(sw, csvFormat)) {
        value.forEach(it -> {
          try {
            final Object[] test = Arrays.stream(it.getClass().getDeclaredFields()).map(field -> {
                try {
                    field.setAccessible(true);
//                    final var fieldClass = field.getType();
                    final var deserializerFunction = (DeserializerFunction)getDeserializerFunction(field);
                    return deserializerFunction.deserialize(field.get(it));
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }).toArray(Object[]::new);
            printer.printRecord(test);
        } catch (IOException e) {
              throw new RuntimeException(e);
          }});
      } catch (IOException e) {
        System.out.println(e.getMessage());;
      }
      return sw.toString();
    }
    return null;
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
      field.set(mappedObject, getSerializerFunction(field).serialize(value));
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

}
