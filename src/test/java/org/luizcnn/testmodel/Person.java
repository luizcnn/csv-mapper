package org.luizcnn.testmodel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.luizcnn.annotations.CsvProperty;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Person {
  @CsvProperty
  private UUID id;
  @CsvProperty(name = "nome")
  private String name;
  @CsvProperty
  private String email;
  @CsvProperty(name = "idade")
  private int age;
  @CsvProperty(name = "ehRegistrado")
  private Boolean isRegistered;
  @CsvProperty(name = "saldo")
  private BigDecimal balance;

  private String notMappedField;
}
