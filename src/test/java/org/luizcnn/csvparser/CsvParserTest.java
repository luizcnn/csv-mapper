package org.luizcnn.csvparser;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.luizcnn.utils.io.CsvReader.readCsvFrom;
import static org.luizcnn.utils.template.PersonTemplate.buildPerson;

class CsvParserTest {

  private static final Path TEST_DATA_PATH = Path.of("src", "test", "resources", "test-data");

  @Test
  public void shouldConvertCSVIntoListOfMap() {
    //arrange
    final var firstPerson = buildPerson(
            "joaozinho", 19, BigDecimal.valueOf(10.21), true, LocalDate.of(2004,2,23)
    );
    final var secondPerson = buildPerson(
            "maria", 34, BigDecimal.valueOf(42.23), false, LocalDate.of(1989,3,1)
    );
    final var expectedPersons = List.of(firstPerson, secondPerson);
    final var csvContent = readCsvFrom(TEST_DATA_PATH, "person.csv");

    //act
    final var result = CsvParser.parseCsvAsListOfMap(csvContent);

    //asserts
    assertNotNull(result);
    assertEquals(2, result.size());
    for (int i = 0; i < result.size(); i++) {
      final var rowAsMap = result.get(i);
      final var expectedPerson = expectedPersons.get(i);
      assertEquals(expectedPerson.getName(), rowAsMap.get("nome"));
      assertEquals(String.valueOf(expectedPerson.getAge()), rowAsMap.get("idade"));
      assertEquals(expectedPerson.getId().toString(), rowAsMap.get("id"));
      assertEquals(expectedPerson.getBalance().toString(), rowAsMap.get("saldo"));
      assertEquals(expectedPerson.getIsRegistered().toString(), rowAsMap.get("ehRegistrado"));
    }
  }

}