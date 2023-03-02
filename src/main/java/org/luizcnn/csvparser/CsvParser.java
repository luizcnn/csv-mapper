package org.luizcnn.csvparser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class CsvParser {

  public static List<Map<String, String>> parseCsvAsListOfMap(String csvContent) {
    final List<Map<String, String>> result = new ArrayList<>();
    final var rows = Arrays
            .stream(csvContent.trim().split("\n"))
            .collect(Collectors.toCollection(LinkedList::new));

    final var headers = Arrays.asList(
            Objects.requireNonNull(rows.poll()).split(",")
    );

    rows.forEach(row -> {
      final var rowAsList = Arrays.asList(row.split(","));
      final Map<String, String> mapOfRow = new HashMap<>();
      for (int i = 0; i < headers.size(); i++) {
        mapOfRow.put(headers.get(i), rowAsList.get(i));
      }
      result.add(mapOfRow);
    });

    return result;
  }

}
