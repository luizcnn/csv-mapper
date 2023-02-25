package org.luizcnn.utils.io;

import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

public class CsvReader {

  public static String readCsvFrom(Path path) {
    try(final var stream = Files.lines(path)) {
      return stream.collect(Collectors.joining("\n"));
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public static String readCsvFrom(Path path, String fileName) {
    try(final var stream = Files.lines(path.resolve(fileName))) {
      return stream.collect(Collectors.joining("\n"));
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
