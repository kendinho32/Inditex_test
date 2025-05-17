package com.inditex.inditex_test.integration;

import com.inditex.inditex_test.modules.price.entities.PriceEntity;
import com.inditex.inditex_test.modules.price.records.PricesRecord;
import com.inditex.inditex_test.modules.price.repositories.PriceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class IntegrationFindPrices {

    @Autowired
    private PriceRepository priceRepository;
    @Autowired
    private WebTestClient webTestClient;

    @BeforeEach
    public void setUp() {
        priceRepository.findAll()
                .thenMany(Flux.just(buildPrice()))
                .flatMap(priceRepository::save)
                .blockLast();
    }

    @Test
    void shouldReturnAllItems() {
        webTestClient.get()
                .uri("/prices")
                .exchange()
                .expectStatus()
                .isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBodyList(PricesRecord.class)
                .hasSize(1);
    }

    private PriceEntity buildPrice() {
        String dateString = "2020-06-14T00:00:00";
        LocalDateTime dateTime = LocalDateTime.parse(dateString);

        PriceEntity price = new PriceEntity();
        price.setId(1L);
        price.setBrandId(1L);
        price.setName("Brand1");
        price.setPriceList(1L);
        price.setProductId(1L);
        price.setPriority(1L);
        price.setPrice(250.0);
        price.setCurr("EUR");
        price.setStartDate(dateTime);
        price.setEndDate(dateTime.plusDays(2));

        return price;
    }
}
