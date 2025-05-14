package com.inditex.inditex_test.modules.price.infraestructure.controllers;


import com.inditex.inditex_test.modules.price.usecases.FindPrices;
import com.inditex.inditex_test.modules.price.usecases.PriceApply;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
public class PricesController {

  private final FindPrices findPrices;
  private final PriceApply priceApply;

    /**
   * Find all prices save in the database.
   *
   * @return CampaignsRecord.
   */
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
