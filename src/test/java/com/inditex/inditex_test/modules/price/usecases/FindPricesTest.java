package com.inditex.inditex_test.modules.price.usecases;

import com.inditex.inditex_test.modules.price.entities.PriceEntity;
import com.inditex.inditex_test.modules.price.records.PricesRecord;
import com.inditex.inditex_test.modules.price.repositories.PriceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;

import static org.mockito.Mockito.when;

class FindPricesTest {

    @Mock
    private PriceRepository priceRepository;
    @InjectMocks
    private FindPrices  findPrices;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void findPrices_whenRepositoryException_thenReturnError() {
        // Arrange
        when(priceRepository.findAll()).thenReturn(Flux.error(new RuntimeException("FAKE EXCEPTION")));

        // Act
        Mono<PricesRecord> thrown = findPrices.findPrices();

        // Assert
        StepVerifier.create(thrown)
                .expectErrorMatches(throwable -> throwable instanceof RuntimeException &&
                        throwable.getMessage().equals("FAKE EXCEPTION"))
                .verify();
    }

    @Test
    public void findPrices_NoPricesFound_ShouldReturnDefaultPriceRecord() {
        // Arrange
        when(priceRepository.findAll()).thenReturn(Flux.empty());

        // Act
        Mono<PricesRecord> result = findPrices.findPrices();

        // Assert
        StepVerifier.create(result)
                .expectNextMatches(pricesRecord -> pricesRecord.prices().isEmpty())
                .verifyComplete();
    }

    @Test
    public void findPriceApply_PricesFound_ShouldReturnHighestPriorityPriceRecord() {
        // Arrange
        LocalDateTime dateTime = LocalDateTime.now();

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

        when(priceRepository.findAll()).thenReturn(Flux.just(price1, price2));

        // Act
        Mono<PricesRecord> result = findPrices.findPrices();

        // Assert
        StepVerifier.create(result)
                .expectNextMatches(pricesRecord ->
                    pricesRecord.prices().size() == 2
                )
                .verifyComplete();
    }
}