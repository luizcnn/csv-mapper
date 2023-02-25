package org.luizcnn.io;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

public class CsvReader {

  public static String readCsvFromResource(String fileName) {
    try(final var stream = Files.lines(Path.of("src", "main", "resources", fileName))) {
      return stream.collect(Collectors.joining("\n"));
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public static String readCsvFromPath(Path path) {
    try(final var stream = Files.lines(path)) {
      return stream.collect(Collectors.joining("\n"));
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
