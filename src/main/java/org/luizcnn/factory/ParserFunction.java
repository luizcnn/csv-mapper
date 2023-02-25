package org.luizcnn.factory;

@FunctionalInterface
public interface ParserFunction<T> {
  T apply(String value);
}
