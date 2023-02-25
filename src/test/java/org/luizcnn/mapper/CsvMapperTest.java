package org.luizcnn.mapper;

import org.junit.jupiter.api.Test;
import org.luizcnn.testmodel.Person;

import java.math.BigDecimal;
import java.nio.file.Path;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.luizcnn.utils.io.CsvReader.readCsvFrom;

class CsvMapperTest {

//  private final CsvMapper csvMapper = new CsvMapper();

  private static final Path TEST_DATA_PATH = Path.of("src", "test", "resources", "test-data");

  @Test
  public void shouldMapCsvWithinDataOfPersonsIntoAListOfPersonModel() {
    //arrange
    final var firstPerson = buildPerson("joaozinho", 19, BigDecimal.valueOf(10.21), true);
    final var secondPerson = buildPerson("maria", 34, BigDecimal.valueOf(42.23), false);
    final var expectedPersons = List.of(firstPerson, secondPerson);
    final var csvContent = readCsvFrom(TEST_DATA_PATH, "person.csv");

    //act
    final var persons = CsvMapper.getInstance().process(csvContent, Person.class);

    //asserts
    assertNotNull(persons);
    assertEquals(2, persons.size());
    for (int i = 0; i < persons.size(); i++) {
      final var expectedPerson = expectedPersons.get(i);
      final var person = persons.get(i);
      assertPersons(expectedPerson, person);
    }
  }

  private Person buildPerson(String name, Integer age, BigDecimal balance, boolean isRegistered) {
    return Person.builder()
            .id(UUID.nameUUIDFromBytes(name.getBytes()))
            .name(name)
            .email(name.concat("@email.com"))
            .age(age)
            .isRegistered(isRegistered)
            .balance(balance)
            .build();
  }

  private static void assertPersons(Person expectedPerson, Person person) {
    assertEquals(expectedPerson.getName(), person.getName());
    assertEquals(expectedPerson.getAge(), person.getAge());
    assertEquals(expectedPerson.getId(), person.getId());
    assertEquals(expectedPerson.getBalance(), person.getBalance());
    assertEquals(expectedPerson.getIsRegistered(), person.getIsRegistered());
  }
}