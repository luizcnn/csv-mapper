package org.luizcnn.testmodel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.luizcnn.annotations.CsvHeader;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Person {
  @CsvHeader(name = "id", position = 0)
  private UUID id;
  @CsvHeader(name = "nome", position = 1)
  private String name;
  @CsvHeader(name = "email", position = 2)
  private String email;
  @CsvHeader(name = "idade", position = 3)
  private int age;
  @CsvHeader(name = "ehRegistrado", position = 4)
  private Boolean isRegistered;
  @CsvHeader(name = "saldo", position = 5)
  private BigDecimal balance;
}
