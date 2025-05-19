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
public class IntegrationPricesApplyTest {

    public static final String ZARA = "ZARA";
    public static final long PRODUCT_ID = 35455L;
    public static final long BRAND_ID = 1L;

    private static final String URI_TEMPLATE = "/apply_date/%s/product_id/%s/brand_id/%s/price";

    @Autowired
    private WebTestClient webTestClient;

    @BeforeEach
    public void setUp() {}

    @ParameterizedTest
    @MethodSource("dataTest")
    void pricesApply_casesParametrized(String date, String productId, String brandId, Double priceExpected,
                                       String startDateExpected, String endDateExpected) {
        webTestClient.get()
                .uri(String.format(URI_TEMPLATE, date, productId, brandId))
                .exchange()
                .expectStatus()
                .isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(PriceRecord.class)
                .value(price -> {
                    assertThat(price.name()).isEqualTo(ZARA);
                    assertThat(price.brandId()).isEqualTo(BRAND_ID);
                    assertThat(price.productId()).isEqualTo(PRODUCT_ID);
                    assertThat(price.price()).isEqualTo(priceExpected);
                    assertThat(price.startDate()).isEqualTo(LocalDateTime.parse(startDateExpected));
                    assertThat(price.endDate()).isEqualTo(LocalDateTime.parse(endDateExpected));
                });
    }

    private static Stream<Arguments> dataTest() {
        return Stream.of(
                Arguments.of("2020-06-14T10:00:00", String.valueOf(PRODUCT_ID), String.valueOf(BRAND_ID), 35.5, "2020-06-14T00:00:00", "2020-12-31T23:59:59"),
                Arguments.of("2020-06-14T16:00:00", String.valueOf(PRODUCT_ID), String.valueOf(BRAND_ID), 25.45, "2020-06-14T15:00:00", "2020-06-14T18:30:00"),
                Arguments.of("2020-06-14T21:00:00", String.valueOf(PRODUCT_ID), String.valueOf(BRAND_ID), 35.5, "2020-06-14T00:00:00", "2020-12-31T23:59:59"),
                Arguments.of("2020-06-15T10:00:00", String.valueOf(PRODUCT_ID), String.valueOf(BRAND_ID), 30.5, "2020-06-15T00:00:00", "2020-06-15T11:00:00"),
                Arguments.of("2020-06-16T21:00:00", String.valueOf(PRODUCT_ID), String.valueOf(BRAND_ID), 38.95, "2020-06-15T16:00:00", "2020-12-31T23:59:59")
        );
    }
}
