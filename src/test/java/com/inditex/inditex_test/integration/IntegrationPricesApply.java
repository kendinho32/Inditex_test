package com.inditex.inditex_test.integration;

import com.inditex.inditex_test.modules.price.records.PriceRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class IntegrationPricesApply {

    @Autowired
    private WebTestClient webTestClient;

    @BeforeEach
    public void setUp() {}

    @Test
    void pricesApply_whenCaseFirstTest() {
        webTestClient.get()
                .uri("/apply_date/2020-06-14T10:00:00/product_id/35455/brand_id/1/price")
                .exchange()
                .expectStatus()
                .isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(PriceRecord.class)
                .value(price -> {
                    assertThat(price.name()).isEqualTo("ZARA");
                    assertThat(price.brandId()).isEqualTo(1L);
                    assertThat(price.productId()).isEqualTo(35455L);
                    assertThat(price.price()).isEqualTo(35.5);
                    assertThat(price.startDate()).isEqualTo(LocalDateTime.parse("2020-06-14T00:00:00"));
                    assertThat(price.endDate()).isEqualTo(LocalDateTime.parse("2020-12-31T23:59:59"));
                });
    }

    @Test
    void pricesApply_whenCaseSecondTest() {
        webTestClient.get()
                .uri("/apply_date/2020-06-14T16:00:00/product_id/35455/brand_id/1/price")
                .exchange()
                .expectStatus()
                .isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(PriceRecord.class)
                .value(price -> {
                    assertThat(price.name()).isEqualTo("ZARA");
                    assertThat(price.brandId()).isEqualTo(1L);
                    assertThat(price.priority()).isEqualTo(1L);
                    assertThat(price.productId()).isEqualTo(35455L);
                    assertThat(price.price()).isEqualTo(25.45);
                    assertThat(price.startDate()).isEqualTo(LocalDateTime.parse("2020-06-14T15:00:00"));
                    assertThat(price.endDate()).isEqualTo(LocalDateTime.parse("2020-06-14T18:30:00"));
                });
    }

    @Test
    void pricesApply_whenCaseThreeTest() {
        webTestClient.get()
                .uri("/apply_date/2020-06-14T21:00:00/product_id/35455/brand_id/1/price")
                .exchange()
                .expectStatus()
                .isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(PriceRecord.class)
                .value(price -> {
                    assertThat(price.name()).isEqualTo("ZARA");
                    assertThat(price.brandId()).isEqualTo(1L);
                    assertThat(price.priority()).isEqualTo(0L);
                    assertThat(price.productId()).isEqualTo(35455L);
                    assertThat(price.price()).isEqualTo(35.5);
                    assertThat(price.startDate()).isEqualTo(LocalDateTime.parse("2020-06-14T00:00:00"));
                    assertThat(price.endDate()).isEqualTo(LocalDateTime.parse("2020-12-31T23:59:59"));
                });
    }

    @Test
    void pricesApply_whenCaseFourTest() {
        webTestClient.get()
                .uri("/apply_date/2020-06-15T10:00:00/product_id/35455/brand_id/1/price")
                .exchange()
                .expectStatus()
                .isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(PriceRecord.class)
                .value(price -> {
                    assertThat(price.name()).isEqualTo("ZARA");
                    assertThat(price.brandId()).isEqualTo(1L);
                    assertThat(price.priority()).isEqualTo(1L);
                    assertThat(price.productId()).isEqualTo(35455L);
                    assertThat(price.price()).isEqualTo(30.5);
                    assertThat(price.startDate()).isEqualTo(LocalDateTime.parse("2020-06-15T00:00:00"));
                    assertThat(price.endDate()).isEqualTo(LocalDateTime.parse("2020-06-15T11:00:00"));
                });
    }

    @Test
    void pricesApply_whenCaseFiveTest() {
        webTestClient.get()
                .uri("/apply_date/2020-06-16T21:00:00/product_id/35455/brand_id/1/price")
                .exchange()
                .expectStatus()
                .isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(PriceRecord.class)
                .value(price -> {
                    assertThat(price.name()).isEqualTo("ZARA");
                    assertThat(price.brandId()).isEqualTo(1L);
                    assertThat(price.priority()).isEqualTo(1L);
                    assertThat(price.productId()).isEqualTo(35455L);
                    assertThat(price.price()).isEqualTo(38.95);
                    assertThat(price.startDate()).isEqualTo(LocalDateTime.parse("2020-06-15T16:00:00"));
                    assertThat(price.endDate()).isEqualTo(LocalDateTime.parse("2020-12-31T23:59:59"));
                });
    }
}
