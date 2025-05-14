package com.inditex.inditex_test.modules.price.usecases;

import com.inditex.inditex_test.modules.price.records.PriceRecord;
import com.inditex.inditex_test.modules.price.records.PricesRecord;
import com.inditex.inditex_test.modules.price.repositories.PriceRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@AllArgsConstructor
public class FindPrices extends CommonCases {

  public static final String NO_PRICES_FOUND = "No prices found";
  public static final String ERROR_TO_FIND_PRICES = "Error to find prices ";
  private final PriceRepository priceRepository;

  /**
   * Find All prices in the database.
   *
   * @return CampaignsRecord.
   */
  public Mono<PricesRecord> findPrices() {
    return priceRepository.findAll()
        .collectList()
        .map(campaignEntity -> {
          if (campaignEntity.isEmpty()) {
            log.warn(NO_PRICES_FOUND);
            return new PricesRecord(new ArrayList<>());
          }

          return buildPriceRecord(campaignEntity);
        })
        .onErrorResume(throwable -> {
          log.error(ERROR_TO_FIND_PRICES, throwable);
          return Mono.error(throwable);
        });
  }
}
