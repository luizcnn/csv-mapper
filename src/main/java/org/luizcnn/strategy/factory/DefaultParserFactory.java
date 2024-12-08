package org.luizcnn.strategy.factory;

import org.luizcnn.exceptions.ParserNotFoundException;
import org.luizcnn.strategy.SerializerFunction;
import org.luizcnn.strategy.serializers.BigDecimalSerializer;
import org.luizcnn.strategy.serializers.BooleanSerializer;
import org.luizcnn.strategy.serializers.DoubleSerializer;
import org.luizcnn.strategy.serializers.FloatSerializer;
import org.luizcnn.strategy.serializers.IntegerSerializer;
import org.luizcnn.strategy.serializers.LocalDateSerializer;
import org.luizcnn.strategy.serializers.LocalDateTimeSerializer;
import org.luizcnn.strategy.serializers.StringSerializer;
import org.luizcnn.strategy.serializers.UUIDSerializer;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public final class DefaultParserFactory {

  public static SerializerFunction<?> getByFieldType(Class<?> fieldType) {
    if (String.class.equals(fieldType)) {
      return StringSerializer.getInstance();
    } else if (Integer.class.equals(fieldType) || int.class.equals(fieldType)) {
      return IntegerSerializer.getInstance();
    } else if (Double.class.equals(fieldType) || double.class.equals(fieldType)) {
      return DoubleSerializer.getInstance();
    } else if (Float.class.equals(fieldType) || float.class.equals(fieldType)) {
      return FloatSerializer.getInstance();
    } else if (Boolean.class.equals(fieldType) || boolean.class.equals(fieldType)) {
      return BooleanSerializer.getInstance();
    } else if (UUID.class.equals(fieldType)) {
      return UUIDSerializer.getInstance();
    } else if (BigDecimal.class.equals(fieldType)) {
      return BigDecimalSerializer.getInstance();
    } else if (LocalDate.class.equals(fieldType)) {
      return LocalDateSerializer.getInstance();
    } else if (LocalDateTime.class.equals(fieldType)) {
      return LocalDateTimeSerializer.getInstance();
    } else {
      throw new ParserNotFoundException(
              String.format(
                  "Does not exists parser function for %s. You could create a custom parser using @CsvPropertyParser",
                  fieldType
              )
      );
    }
  }

}
