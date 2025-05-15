package com.inditex.inditex_test.modules.price.infraestructure.controllers;


import com.inditex.inditex_test.modules.price.usecases.FindPrices;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
public class PricesController {

  private final FindPrices findPrices;

  @Operation(
          summary = "find all registers prices in BD",
          description = "Returns a response with list of prices and status code.")
  @ApiResponses(value = {
          @io.swagger.v3.oas.annotations.responses.ApiResponse(
                  responseCode = "200",
                  description = "Successful response with user information of prices.")
  })
  @GetMapping("/prices")
  public Mono<ResponseEntity> findAllPrices() {
    return findPrices
            .findPrices()
            .map(ResponseEntity::ok);
  }

}
