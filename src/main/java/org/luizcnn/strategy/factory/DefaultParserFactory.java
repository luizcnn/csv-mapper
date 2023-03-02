package org.luizcnn.strategy.factory;

import org.luizcnn.exceptions.ParserNotFoundException;
import org.luizcnn.strategy.ParserFunction;
import org.luizcnn.strategy.parsers.BigDecimalParser;
import org.luizcnn.strategy.parsers.BooleanParser;
import org.luizcnn.strategy.parsers.DoubleParser;
import org.luizcnn.strategy.parsers.FloatParser;
import org.luizcnn.strategy.parsers.IntegerParser;
import org.luizcnn.strategy.parsers.LocalDateParser;
import org.luizcnn.strategy.parsers.LocalDateTimeParser;
import org.luizcnn.strategy.parsers.StringParser;
import org.luizcnn.strategy.parsers.UUIDParser;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public final class DefaultParserFactory {

  public static ParserFunction<?> getByFieldType(Class<?> fieldType) {
    if (String.class.equals(fieldType)) {
      return StringParser.getInstance();
    } else if (Integer.class.equals(fieldType) || int.class.equals(fieldType)) {
      return IntegerParser.getInstance();
    } else if (Double.class.equals(fieldType) || double.class.equals(fieldType)) {
      return DoubleParser.getInstance();
    } else if (Float.class.equals(fieldType) || float.class.equals(fieldType)) {
      return FloatParser.getInstance();
    } else if (Boolean.class.equals(fieldType) || boolean.class.equals(fieldType)) {
      return BooleanParser.getInstance();
    } else if (UUID.class.equals(fieldType)) {
      return UUIDParser.getInstance();
    } else if (BigDecimal.class.equals(fieldType)) {
      return BigDecimalParser.getInstance();
    } else if (LocalDate.class.equals(fieldType)) {
      return LocalDateParser.getInstance();
    } else if (LocalDateTime.class.equals(fieldType)) {
      return LocalDateTimeParser.getInstance();
    } else {
      throw new ParserNotFoundException(String.format("Does not exists parser function for %s", fieldType));
    }
  }

}
