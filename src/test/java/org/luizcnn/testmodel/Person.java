package org.luizcnn.testmodel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.luizcnn.annotations.CsvHeader;
import org.luizcnn.models.CsvSchema;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Person implements CsvSchema {
  @CsvHeader(name = "id", position = 0, type = UUID.class)
  private UUID id;
  @CsvHeader(name = "nome", position = 1, type = String.class)
  private String name;
  @CsvHeader(name = "email", position = 2, type = String.class)
  private String email;
  @CsvHeader(name = "idade", position = 3, type = int.class)
  private int age;
  @CsvHeader(name = "ehRegistrado", position = 4, type = boolean.class)
  private Boolean isRegistered;
  @CsvHeader(name = "saldo", position = 5, type = BigDecimal.class)
  private BigDecimal balance;
}
