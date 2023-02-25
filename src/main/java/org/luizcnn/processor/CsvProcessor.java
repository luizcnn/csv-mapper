package org.luizcnn.processor;

import org.luizcnn.annotations.CsvHeader;
import org.luizcnn.models.CsvSchema;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.luizcnn.strategy.ParserFunctionStrategy.getParserFunction;

public class CsvProcessor<T extends CsvSchema> {

  public List<T> process(String csv, Class<T> targetClass) {
    List<T> result = new ArrayList<>();
    final var csvHeaders = getCsvHeaders(targetClass);
    Constructor<T> objectConstructor = getTargetConstructor(targetClass);
    final var csvAsList = Arrays.stream(csv.trim().split("\n"))
            .map(row -> Arrays.asList(row.split(",")))
            .collect(Collectors.toList());

    validateHeadersPositions(csvAsList.get(0), csvHeaders);

    for(int i = 1; i < csvAsList.size(); i++) { //Skipping header of csv.
      final var rowAsList = csvAsList.get(i);
      final var parameters = new Object[csvHeaders.size()];
      csvHeaders.forEach(header -> {
        int position = header.position();
        final var value = rowAsList.get(position);
        parameters[position] = getParserFunction(header.type()).parse(value);
      });
      try {
        final var instance = objectConstructor.newInstance(parameters);
        result.add(instance);
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    }

    return result;
  }

  private Constructor<T> getTargetConstructor(Class<T> targetClass) {
    try {
      return targetClass.getConstructor(
              Arrays.stream(targetClass.getDeclaredFields())
                      .map(Field::getType)
                      .toArray(Class[]::new)
      );
    } catch (NoSuchMethodException e) {
      throw new RuntimeException(e);
    }
  }

  private List<CsvHeader> getCsvHeaders(Class<T> targetClass) {
    return Stream
            .of(targetClass.getDeclaredFields())
            .map(field -> field.getAnnotation(CsvHeader.class))
            .collect(Collectors.toList());
  }

  private static void validateHeadersPositions(List<String> csvHeaders, List<CsvHeader> headers) {
    final var validator = headers
            .stream()
            .map(CsvHeader::name)
            .collect(Collectors.toList());

    for(int i = 0; i < csvHeaders.size(); i++) {
      if (!csvHeaders.get(i).equals(validator.get(i))) {
        throw new RuntimeException("CSV file headers does not correspond to Schema. CsvSchemaHeaders:" + headers);
      }
    }
  }

}
