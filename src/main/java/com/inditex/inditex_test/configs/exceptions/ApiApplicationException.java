package com.inditex.inditex_test.configs.exceptions;

import org.springframework.http.HttpStatus;

public class ApiApplicationException extends ApiException {
  private static final long serialVersionUID = -525546673970530803L;

  private static final String INTERNAL_ERROR_CODE = "internal_error";

  /**
   * Creates a new instance, with provided cause.
   *
   * @param e cause
   */
  public ApiApplicationException(Throwable e) {
    super(
        INTERNAL_ERROR_CODE,
        HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
        HttpStatus.INTERNAL_SERVER_ERROR.value(),
        e);
  }

  /**
   * Creates a new instance, with provided message.
   *
   * @param message custom message.
   */
  public ApiApplicationException(String message) {
    super(INTERNAL_ERROR_CODE, message, HttpStatus.INTERNAL_SERVER_ERROR.value());
  }

  /**
   * Creates a new instance, with provided cause and custom message.
   *
   * @param message custom message.
   * @param cause cause.
   */
  public ApiApplicationException(String message, Throwable cause) {
    super(INTERNAL_ERROR_CODE, message, HttpStatus.INTERNAL_SERVER_ERROR.value(), cause);
  }
}
