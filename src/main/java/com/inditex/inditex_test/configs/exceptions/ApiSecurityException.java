package com.inditex.inditex_test.configs.exceptions;

import org.springframework.http.HttpStatus;

public class ApiSecurityException extends ApiException {

  private static final long serialVersionUID = -525546673970530804L;
  private static final String FORBIDDEN_ERROR = "forbidden";

  /**
   * Creates a new instance, with provided cause.
   *
   * @param cause cause
   */
  public ApiSecurityException(Throwable cause) {
    super(
        FORBIDDEN_ERROR,
        HttpStatus.FORBIDDEN.getReasonPhrase(),
        HttpStatus.FORBIDDEN.value(),
        cause);
  }

  /**
   * Creates a new instance, with provided message.
   *
   * @param message custom message.
   */
  public ApiSecurityException(String message) {
    super(FORBIDDEN_ERROR, message, HttpStatus.FORBIDDEN.value());
  }

  /**
   * Creates a new instance, with provided message.
   *
   * @param message custom message.
   * @param cause cause
   */
  public ApiSecurityException(String message, Throwable cause) {
    super(FORBIDDEN_ERROR, message, HttpStatus.FORBIDDEN.value(), cause);
  }
}
