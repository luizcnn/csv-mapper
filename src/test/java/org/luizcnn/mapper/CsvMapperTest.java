package org.luizcnn.mapper;

import org.junit.jupiter.api.Test;
import org.luizcnn.testmodel.BrazilianPerson;
import org.luizcnn.testmodel.Person;

import java.math.BigDecimal;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.luizcnn.utils.io.CsvReader.readCsvFrom;
import static org.luizcnn.utils.template.PersonTemplate.buildPerson;

class CsvMapperTest {

  private static final Path TEST_DATA_PATH = Path.of("src", "test", "resources", "test-data");

  @Test
  public void shouldMapCsvWithinDataOfPersonsIntoAListOfPersonModel() {
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
    final var persons = CsvMapper.getInstance().fromCSV(csvContent, Person.class);

    //asserts
    assertNotNull(persons);
    assertEquals(2, persons.size());
    for (int i = 0; i < persons.size(); i++) {
      final var expectedPerson = expectedPersons.get(i);
      final var person = persons.get(i);

      assertPersons(expectedPerson, person);
    }
  }

  @Test
  public void shouldMapCsvWithinDataOfPersonsIntoAListOfPersonBrazilianBirthdateModel() {
    //arrange
    final var firstPerson = BrazilianPerson
            .builder()
            .name("joaozinho")
            .birthDate(LocalDate.of(2004,2,23))
            .build();
    final var secondPerson = BrazilianPerson
            .builder()
            .name("maria")
            .birthDate(LocalDate.of(1989,3,1))
            .build();
    final var expectedPersons = List.of(firstPerson, secondPerson);
    final var csvContent = readCsvFrom(TEST_DATA_PATH, "brazilian-person.csv");

    //act
    final var persons = CsvMapper.getInstance().fromCSV(csvContent, BrazilianPerson.class);

    //asserts
    assertNotNull(persons);
    assertEquals(2, persons.size());
    for (int i = 0; i < persons.size(); i++) {
      final var expectedPerson = expectedPersons.get(i);
      final var person = persons.get(i);
      assertEquals(expectedPerson.getName(), person.getName());
      assertEquals(expectedPerson.getBirthDate(), person.getBirthDate());
    }
  }

  @Test
  public void shouldMapACollectionOfObjectToACSVString() {
    //arrange
    final var firstPerson = BrazilianPerson
            .builder()
            .name("joaozinho")
            .birthDate(LocalDate.of(2004,2,23))
            .build();
    final var secondPerson = BrazilianPerson
            .builder()
            .name("maria")
            .birthDate(LocalDate.of(1989,3,1))
            .build();
    final var expectedCSV = readCsvFrom(TEST_DATA_PATH, "brazilian-person.csv");

    //act
    final var csvContent = CsvMapper.getInstance().writeValueAsString(List.of(firstPerson, secondPerson));
    assertThat(csvContent).isEqualToIgnoringNewLines(expectedCSV);
  }

  private static void assertPersons(Person expectedPerson, Person person) {
    assertEquals(expectedPerson.getName(), person.getName());
    assertEquals(expectedPerson.getAge(), person.getAge());
    assertEquals(expectedPerson.getId(), person.getId());
    assertEquals(expectedPerson.getBalance(), person.getBalance());
    assertEquals(expectedPerson.getIsRegistered(), person.getIsRegistered());
    assertNull(person.getNotMappedField());
  }
}