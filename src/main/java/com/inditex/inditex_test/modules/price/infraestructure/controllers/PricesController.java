package com.inditex.inditex_test.modules.price.infraestructure.controllers;

import com.inditex.inditex_test.modules.price.usecases.FindPrices;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@RestController
@AllArgsConstructor
public class PricesController {

  private final FindPrices findPrices;
  private final PriceApply priceApply;

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

  @GetMapping("/apply_date/{apply_date}/product_id/{product_id}/brand_id/{brand_id}/price")
  public Mono<ResponseEntity> findPriceApply(@PathVariable(name = "apply_date") LocalDateTime applyDate,
                                             @PathVariable(name = "product_id") Long productId,
                                             @PathVariable(name = "brand_id") Long brandId) {
    return priceApply
            .findPriceApply(applyDate, productId, brandId)
            .map(ResponseEntity::ok);

  }

}
