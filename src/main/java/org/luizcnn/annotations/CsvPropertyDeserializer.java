package org.luizcnn.annotations;

import org.luizcnn.strategy.DeserializerFunction;

import java.lang.annotation.*;

@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface CsvPropertyDeserializer {

  Class<? extends DeserializerFunction<?>> using();

}
