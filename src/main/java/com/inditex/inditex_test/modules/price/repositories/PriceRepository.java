package com.inditex.inditex_test.modules.price.repositories;

import com.inditex.inditex_test.modules.price.entities.PriceEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;

/**
 * Repository for CampaignModel.
 */
@Repository
public interface PriceRepository extends ReactiveCrudRepository<PriceEntity, Long> {

    @Query("""
      SELECT * FROM prices
      WHERE LOWER(:date) BETWEEN start_date AND end_date
          AND LOWER(brand_id) = LOWER(:brandId)
          AND LOWER(product_id) = LOWER(:productId)
      
        """)
    Flux<PriceEntity> findPricesBetweenStartDateAndEndDateAndBrandIdAndProductId(LocalDateTime date, Long brandId, Long productId);
}
