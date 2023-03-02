package org.luizcnn.testmodel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.luizcnn.annotations.CsvProperty;
import org.luizcnn.annotations.CsvPropertyParser;
import org.luizcnn.customparsers.BrazilianLocalDateParser;

import java.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BrazilianPerson {

  @CsvProperty(name = "nome")
  private String name;

  @CsvProperty(name = "dataNascimento")
  @CsvPropertyParser(using = BrazilianLocalDateParser.class)
  private LocalDate birthDate;

}
