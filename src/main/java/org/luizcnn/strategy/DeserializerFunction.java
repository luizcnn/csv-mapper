package org.luizcnn.strategy;

public interface DeserializerFunction<T> {

  default String deserialize(T value) {
    return value.toString();
  }

}
