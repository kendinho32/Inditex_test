package com.inditex.inditex_test.modules.ping.controllers;


import com.inditex.inditex_test.modules.user.usecases.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/** Controller responsible for /ping implementation. */
@RestController
@AllArgsConstructor
public class PingController {

  private UserService userService;

  /**
   * @return "pong" String.
   */
  @GetMapping("/ping")
  public String ping() {
    return "pong";
  }

  /**
   * @param userId Long.
   * @return "pong reactive" String.
   */
  @Operation(
          summary = "Ping reactive",
          description = "Returns a reactive ping response with user information.")
  @ApiResponses(value = {
          @io.swagger.v3.oas.annotations.responses.ApiResponse(
                  responseCode = "200",
                  description = "Successful ping response with user information."),
          @io.swagger.v3.oas.annotations.responses.ApiResponse(
                  responseCode = "404",
                  description = "User not found.")
  })
  @GetMapping("/pingReactive/{userId}")
  public Mono<ResponseEntity> pingReactive(@PathVariable Long userId) {
    return userService
            .demoReactive(userId)
            .map(response -> ResponseEntity.status(HttpStatus.OK).body(response));
  }
}
