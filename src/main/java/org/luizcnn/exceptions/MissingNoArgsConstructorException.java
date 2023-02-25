package org.luizcnn.exceptions;

public class MissingNoArgsConstructorException extends RuntimeException {

  public MissingNoArgsConstructorException(Throwable t) {
    this("The target class is required to implement the no-argument constructor", t);
  }

  public MissingNoArgsConstructorException(String msg, Throwable t) {
    super(msg, t);
  }

}
