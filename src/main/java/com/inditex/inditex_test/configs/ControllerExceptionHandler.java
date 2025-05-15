package com.inditex.inditex_test.configs;


import com.inditex.inditex_test.exceptions.ApiException;
import com.inditex.inditex_test.shared.commons.dtos.ApiError;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import reactor.core.publisher.Mono;

/** Basic handling for exceptions. */
@Log4j2
@ControllerAdvice
public class ControllerExceptionHandler {

  /**
   * Handler for external API exceptions.
   *
   * @param exception the exception thrown during a request to external API.
   * @return {@link ResponseEntity} with status code and description provided for the handled
   *     exception.
   */
  @ExceptionHandler(ApiException.class)
  protected Mono<ResponseEntity<ApiError>> handleApiException(ApiException exception) {
    Integer statusCode = exception.getStatusCode();
    boolean expected = HttpStatus.INTERNAL_SERVER_ERROR.value() > statusCode;
    if (expected) {
      log.warn("Handled ApiException. Status Code:{} - Error:{} ", statusCode, exception);
    } else {
      log.error("Unhandled ApiException. Status Code:{} - Error:{}", statusCode, exception);
    }

    ApiError apiError = new ApiError(exception.getCode(), exception.getDescription(), statusCode);
    return Mono.just(
        ResponseEntity.status(apiError.status())
            .contentType(MediaType.APPLICATION_JSON)
            .body(apiError));
  }
}
