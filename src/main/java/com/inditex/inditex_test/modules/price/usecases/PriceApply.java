package com.inditex.inditex_test.modules.price.usecases;

import com.inditex.inditex_test.modules.price.records.PriceRecord;
import com.inditex.inditex_test.modules.price.repositories.PriceRepository;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@AllArgsConstructor
public class PriceApply {
  private final PriceRepository priceRepository;

  public Mono<PriceRecord> findPriceApply(LocalDateTime applyDate,
                                          Long productId,
                                          Long brandId) {
    return priceRepository.findPricesBetweenStartDateAndEndDateAndBrandIdAndProductId(applyDate, brandId, productId)
        .collectList()
        .map(listPrices -> {
          if (listPrices.isEmpty()) {
            log.warn("No prices found");
            return new PriceRecord(0L, "", 0L, 0L, 0L, 0.0, "", null, null);
          }
          listPrices.forEach(price -> {

          });

          return new PriceRecord(0L, "", 0L, 0L, 0L, 0.0, "", null, null);
        });
  }

}
