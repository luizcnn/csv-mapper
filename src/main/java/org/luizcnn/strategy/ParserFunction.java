package org.luizcnn.strategy;

public interface ParserFunction<T> {

  //TODO Create a parser function to Dates (Maybe create an annotation to define custom parsers)
  T parse(String value);

}
