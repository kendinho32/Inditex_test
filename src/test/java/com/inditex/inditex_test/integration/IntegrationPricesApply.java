package com.inditex.inditex_test.integration;

import com.inditex.inditex_test.modules.price.records.PriceRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class IntegrationPricesApply {

    public static final String ZARA = "ZARA";
    @Autowired
    private WebTestClient webTestClient;

    @BeforeEach
    public void setUp() {}

    @ParameterizedTest
    @MethodSource("dataTest")
    void pricesApply_casesParametrized(String date, String productId, String brandId, Double priceExpected,
                                       String startDateExpected, String endDateExpected) {
        webTestClient.get()
                .uri("/apply_date/"+ date +"/product_id/"+ productId +"/brand_id/"+ brandId +"/price")
                .exchange()
                .expectStatus()
                .isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(PriceRecord.class)
                .value(price -> {
                    assertThat(price.name()).isEqualTo(ZARA);
                    assertThat(price.brandId()).isEqualTo(1L);
                    assertThat(price.productId()).isEqualTo(35455L);
                    assertThat(price.price()).isEqualTo(priceExpected);
                    assertThat(price.startDate()).isEqualTo(LocalDateTime.parse(startDateExpected));
                    assertThat(price.endDate()).isEqualTo(LocalDateTime.parse(endDateExpected));
                });
    }

    private static Stream<Arguments> dataTest() {
        return Stream.of(
                Arguments.of("2020-06-14T10:00:00", "35455", "1", 35.5, "2020-06-14T00:00:00", "2020-12-31T23:59:59"),
                Arguments.of("2020-06-14T16:00:00", "35455", "1", 25.45, "2020-06-14T15:00:00", "2020-06-14T18:30:00"),
                Arguments.of("2020-06-14T21:00:00", "35455", "1", 35.5, "2020-06-14T00:00:00", "2020-12-31T23:59:59"),
                Arguments.of("2020-06-15T10:00:00", "35455", "1", 30.5, "2020-06-15T00:00:00", "2020-06-15T11:00:00"),
                Arguments.of("2020-06-16T21:00:00", "35455", "1", 38.95, "2020-06-15T16:00:00", "2020-12-31T23:59:59")
        );
    }
}
