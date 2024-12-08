package org.luizcnn.strategy;

public interface SerializerFunction<T> {

  T serialize(String value);

}
