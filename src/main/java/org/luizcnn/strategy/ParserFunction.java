package org.luizcnn.strategy;

@FunctionalInterface
public interface ParserFunction<T> {
  T parse(String value);

}
