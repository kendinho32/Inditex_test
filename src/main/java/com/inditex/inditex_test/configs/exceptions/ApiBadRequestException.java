package com.inditex.inditex_test.configs.exceptions;

import org.springframework.http.HttpStatus;

public class ApiBadRequestException extends ApiException {

  private static final long serialVersionUID = -525546673970530802L;
  private static final String BAD_REQUEST_ERROR = "bad_request";

  /**
   * Creates a new instance, with provided cause.
   *
   * @param cause cause
   */
  public ApiBadRequestException(Throwable cause) {
    super(
        BAD_REQUEST_ERROR,
        HttpStatus.BAD_REQUEST.getReasonPhrase(),
        HttpStatus.BAD_REQUEST.value(),
        cause);
  }

  /**
   * Creates a new instance, with provided message.
   *
   * @param message custom message.
   */
  public ApiBadRequestException(String message) {
    super(BAD_REQUEST_ERROR, message, HttpStatus.BAD_REQUEST.value());
  }

  /**
   * Creates a new instance, with provided cause and custom message.
   *
   * @param message custom message.
   * @param cause cause.
   */
  public ApiBadRequestException(String message, Throwable cause) {
    super(BAD_REQUEST_ERROR, message, HttpStatus.BAD_REQUEST.value(), cause);
  }
}
