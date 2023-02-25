package org.luizcnn.strategy;

import org.luizcnn.exceptions.ParserNotFoundException;
import org.luizcnn.strategy.parsers.BigDecimalParser;
import org.luizcnn.strategy.parsers.BooleanParser;
import org.luizcnn.strategy.parsers.DoubleParser;
import org.luizcnn.strategy.parsers.FloatParser;
import org.luizcnn.strategy.parsers.IntegerParser;
import org.luizcnn.strategy.parsers.StringParser;
import org.luizcnn.strategy.parsers.UUIDParser;

import java.math.BigDecimal;
import java.util.UUID;

public class ParserFunctionStrategy {

  public static ParserFunction<?> getParserFunction(Class<?> target) {
    if (String.class.equals(target)) {
      return StringParser.getInstance();
    } else if (Integer.class.equals(target) || int.class.equals(target)) {
      return IntegerParser.getInstance();
    } else if (Double.class.equals(target) || double.class.equals(target)) {
      return DoubleParser.getInstance();
    } else if (Float.class.equals(target) || float.class.equals(target)) {
      return FloatParser.getInstance();
    } else if (Boolean.class.equals(target) || boolean.class.equals(target)) {
      return BooleanParser.getInstance();
    } else if (UUID.class.equals(target)) {
      return UUIDParser.getInstance();
    } else if (BigDecimal.class.equals(target)) {
      return BigDecimalParser.getInstance();
    } else {
      throw new ParserNotFoundException(String.format("Does not exists parser function for %s", target));
    }
  }

}
