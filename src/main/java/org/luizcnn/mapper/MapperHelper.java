package org.luizcnn.mapper;

import lombok.Builder;
import lombok.Value;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

@Value
@Builder
public class MapperHelper {

  Field field;
  Annotation annotation;

}
