package com.inditex.inditex_test.modules.price.usecases;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import com.inditex.inditex_test.modules.price.entities.PriceEntity;
import com.inditex.inditex_test.modules.price.records.PriceRecord;
import com.inditex.inditex_test.modules.price.repositories.PriceRepository;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

class PriceApplyTest {

  @Mock
  private PriceRepository priceRepository;
  @InjectMocks
  private PriceApply priceApply;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void findPriceApply_whenRepositoryException_thenReturnError() {
    // Arrange
    when(priceRepository.findPricesBetweenStartDateAndEndDateAndBrandIdAndProductId(any(), anyLong(), anyLong()))
        .thenReturn(Flux.error(new RuntimeException("FAKE EXCEPTION")));

    // Act
    Mono<PriceRecord> thrown = priceApply.findPriceApply(LocalDateTime.now(), 1L, 1L);

    // Assert
    StepVerifier.create(thrown)
        .expectErrorMatches(throwable -> throwable instanceof RuntimeException &&
            throwable.getMessage().equals("FAKE EXCEPTION"))
        .verify();
  }

  @Test
  public void findPriceApply_NoPricesFound_ShouldReturnDefaultPriceRecord() {
    // Arrange
    LocalDateTime dateTime = LocalDateTime.now();
    Long productId = 1L;
    Long brandId = 1L;

    when(priceRepository.findPricesBetweenStartDateAndEndDateAndBrandIdAndProductId(any(), any(), any()))
        .thenReturn(Flux.empty());

    // Act
    Mono<PriceRecord> result = priceApply.findPriceApply(dateTime, productId, brandId);

    // Assert
    StepVerifier.create(result)
        .expectNextMatches(priceRecord -> {
          return priceRecord.brandId() == 0L
              && priceRecord.name().isEmpty()
              && priceRecord.price() == 0.0;
        })
        .verifyComplete();
  }

  @Test
  public void findPriceApply_PricesFound_ShouldReturnHighestPriorityPriceRecord() {
    // Arrange
    LocalDateTime dateTime = LocalDateTime.now();
    Long productId = 1L;
    Long brandId = 1L;

    PriceEntity price1 = new PriceEntity();
    price1.setId(1L);
    price1.setBrandId(1L);
    price1.setName("Brand1");
    price1.setPriceList(1L);
    price1.setProductId(1L);
    price1.setPriority(1L);
    price1.setPrice(250.0);
    price1.setCurr("EUR");
    price1.setStartDate(dateTime.minusDays(2));
    price1.setEndDate(dateTime.plusDays(2));
    PriceEntity price2 = new PriceEntity();
    price2.setId(1L);
    price2.setBrandId(1L);
    price2.setName("Brand1");
    price2.setPriceList(1L);
    price2.setProductId(1L);
    price2.setPriority(0L);
    price2.setPrice(100.0);
    price2.setCurr("EUR");
    price2.setStartDate(dateTime.minusDays(2));
    price2.setEndDate(dateTime.plusDays(2));

    when(priceRepository.findPricesBetweenStartDateAndEndDateAndBrandIdAndProductId(any(), any(), any()))
        .thenReturn(Flux.just(price1, price2));

    // Act
    Mono<PriceRecord> result = priceApply.findPriceApply(dateTime, productId, brandId);

    // Assert
    StepVerifier.create(result)
        .expectNextMatches(priceRecord -> {
          return priceRecord.brandId() == 1L
              && priceRecord.price() == 250.0;
        })
        .verifyComplete();
  }
}