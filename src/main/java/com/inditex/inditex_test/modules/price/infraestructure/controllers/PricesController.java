package com.inditex.inditex_test.modules.price.infraestructure.controllers;

import com.inditex.inditex_test.modules.price.usecases.FindPrices;
import com.inditex.inditex_test.modules.price.usecases.PriceApply;
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
          summary = "Find all prices registered in BD",
          description = "Returns a list of prices and status code.")
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

  @Operation(
          summary = "find Price Apply",
          description = "Returns a register.")
  @ApiResponses(value = {
          @io.swagger.v3.oas.annotations.responses.ApiResponse(
                  responseCode = "200",
                  description = "Successful response with user information of price apply.")
  })
  @GetMapping("/apply_date/{apply_date}/product_id/{product_id}/brand_id/{brand_id}/price")
  public Mono<ResponseEntity> findPriceApply(@PathVariable(name = "apply_date") LocalDateTime applyDate,
                                             @PathVariable(name = "product_id") Long productId,
                                             @PathVariable(name = "brand_id") Long brandId) {
    return priceApply
            .findPriceApply(applyDate, productId, brandId)
            .map(ResponseEntity::ok);
  }

}
