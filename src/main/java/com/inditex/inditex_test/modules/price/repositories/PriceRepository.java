package com.inditex.inditex_test.modules.price.repositories;

import com.inditex.inditex_test.modules.price.entities.PriceEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for CampaignModel.
 */
@Repository
public interface PriceRepository extends ReactiveCrudRepository<PriceEntity, Long> {}
