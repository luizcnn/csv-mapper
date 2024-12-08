package org.luizcnn.testmodel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.luizcnn.annotations.CsvProperty;
import org.luizcnn.annotations.CsvPropertyDeserializer;
import org.luizcnn.annotations.CsvPropertySerializer;
import org.luizcnn.customparsers.BrazilianLocalDateDeserializer;
import org.luizcnn.customparsers.BrazilianLocalDateSerializer;

import java.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BrazilianPerson {

  @CsvProperty(name = "nome")
  private String name;

  @CsvProperty(name = "dataNascimento")
  @CsvPropertySerializer(using = BrazilianLocalDateSerializer.class)
  @CsvPropertyDeserializer(using = BrazilianLocalDateDeserializer.class)
  private LocalDate birthDate;

}
