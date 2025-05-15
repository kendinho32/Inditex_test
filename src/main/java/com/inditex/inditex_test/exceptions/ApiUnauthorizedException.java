package com.inditex.inditex_test.exceptions;

import org.springframework.http.HttpStatus;

public class ApiUnauthorizedException extends ApiException {

  private static final long serialVersionUID = -525546673970530805L;
  private static final String UNAUTHORIZED_ERROR = "unauthorized";

  /**
   * Creates a new instance, with provided cause.
   *
   * @param cause cause
   */
  public ApiUnauthorizedException(Throwable cause) {
    super(
        UNAUTHORIZED_ERROR,
        HttpStatus.UNAUTHORIZED.getReasonPhrase(),
        HttpStatus.UNAUTHORIZED.value(),
        cause);
  }

  /**
   * Creates a new instance, with provided message.
   *
   * @param message custom message.
   */
  public ApiUnauthorizedException(String message) {
    super(UNAUTHORIZED_ERROR, message, HttpStatus.UNAUTHORIZED.value());
  }

  /**
   * Creates a new instance, with provided message.
   *
   * @param message custom message.
   * @param cause cause
   */
  public ApiUnauthorizedException(String message, Throwable cause) {
    super(UNAUTHORIZED_ERROR, message, HttpStatus.UNAUTHORIZED.value(), cause);
  }
}
