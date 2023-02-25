package org.luizcnn.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.luizcnn.annotations.CsvHeader;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockCsvSchema implements CsvSchema {

  @CsvHeader(name = "code", position = 0, type = String.class)
  private String code;

  @CsvHeader(name = "quantity", position = 1, type = double.class)
  private double quantity;

}

