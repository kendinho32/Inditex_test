package com.inditex.inditex_test.exceptions;

import org.springframework.http.HttpStatus;

public class ApiNotFoundException extends ApiException {

  private static final long serialVersionUID = -525546673970530803L;
  private static final String NOT_FOUND_ERROR = "not_found";

  /**
   * Creates a new instance, with provided cause.
   *
   * @param cause cause
   */
  public ApiNotFoundException(Throwable cause) {
    super(
        NOT_FOUND_ERROR,
        HttpStatus.NOT_FOUND.getReasonPhrase(),
        HttpStatus.NOT_FOUND.value(),
        cause);
  }

  /**
   * Creates a new instance, with provided message.
   *
   * @param message custom message.
   */
  public ApiNotFoundException(String message) {
    super(NOT_FOUND_ERROR, message, HttpStatus.NOT_FOUND.value());
  }

  /**
   * Creates a new instance, with provided message.
   *
   * @param message custom message.
   * @param cause cause
   */
  public ApiNotFoundException(String message, Throwable cause) {
    super(NOT_FOUND_ERROR, message, HttpStatus.NOT_FOUND.value(), cause);
  }
}
