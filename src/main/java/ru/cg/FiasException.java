package ru.cg;

/**
 * @author Rinat Suleymanov
 */
public class FiasException extends Exception {

  private static final String MSG = "Something wrong in fias database";

  public FiasException() {
    super(MSG);
  }
}
