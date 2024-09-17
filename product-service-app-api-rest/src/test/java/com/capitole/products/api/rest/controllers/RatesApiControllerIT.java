package com.capitole.products.api.rest.controllers;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;

import com.capitole.products.application.queries.FindRateByProductIdAndApplicationDateAndBrandIdQuery;
import com.capitole.products.application.queries.FindRateByProductIdAndApplicationDateAndBrandIdQueryHandler;
import com.capitole.products.domain.exceptions.RateNotFoundException;
import com.capitole.products.domain.model.Amount;
import com.capitole.products.domain.model.Rate;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@WebFluxTest
@ContextConfiguration(classes = {RatesApiController.class, RatesRestExceptionHandler.class})
class RatesApiControllerIT {

  @MockBean
  private FindRateByProductIdAndApplicationDateAndBrandIdQueryHandler findRateByProductIdAndApplicationDateAndBrandIdQueryHandler;

  @SpyBean
  private RateDtoMapperImpl rateDtoMapper;

  @Autowired
  private WebTestClient testClient;

  private static Stream<Arguments> provideTestCases() {
    return Stream.of(
        Arguments.of("2020-6-14 10:00", 35455, 1,
            35455, 1, "35.50",
            "2020-06-14 00:00:00", "2020-12-31 23:59:59"),

        Arguments.of("2020-6-14 16:00", 35455, 1,
            35455, 1, "25.45",
            "2020-06-14 15:00:00", "2020-06-14 18:30:00"),

        Arguments.of("2020-6-15 10:00", 35455, 1,
            35455, 1, "30.50",
            "2020-06-15 00:00:00", "2020-06-15 11:00:00"),

        Arguments.of("2020-6-15 21:00", 35455, 1,
            35455, 1, "38.95",
            "2020-06-15 16:00:00", "2020-12-31 23:59:59")
    );
  }

  @ParameterizedTest
  @MethodSource("provideTestCases")
  void getRateByProductIdAndBrandIdAndDate_ShouldReturn200_OnSuccess(String applicationDate,
      Integer productId, Integer brandId, Integer productIdExpected,
      Integer brandIdExpected, String priceExpected,
      String startDateExpected, String endDateExpected) {

    // Given
    FindRateByProductIdAndApplicationDateAndBrandIdQuery query = FindRateByProductIdAndApplicationDateAndBrandIdQuery.builder()
        .applicationDate(applicationDate)
        .brandId(brandId)
        .productId(productId)
        .build();

    Rate rateExpected = Rate.builder()
        .productId(productIdExpected)
        .brandId(brandIdExpected)
        .price(Amount.from(priceExpected, "EUR"))
        .startDate(startDateExpected)
        .endDate(endDateExpected)
        .build();

    Mono<Rate> monoRate = Mono.just(rateExpected);

    given(findRateByProductIdAndApplicationDateAndBrandIdQueryHandler.execute(query))
        .willReturn(monoRate);

    // When
    testClient.get()
        .uri(String.format("/v1/rates?applicationDate=%s&productId=%d&brandId=%d",
            applicationDate, productId, brandId))
        .accept(APPLICATION_JSON)
        .exchange()
        .expectStatus().isOk()
        .expectHeader().contentType(APPLICATION_JSON)
        .expectBody(Rate.class)
        .consumeWith(response -> {

          // Then
          Rate responseBody = response.getResponseBody();
          assertThat(responseBody).isNotNull();

          assertThat(responseBody.productId()).isEqualTo(rateExpected.productId());
          assertThat(responseBody.brandId()).isEqualTo(rateExpected.brandId());
          assertThat(responseBody.price()).isEqualTo(rateExpected.price());
          assertThat(responseBody.startDate()).isEqualTo(rateExpected.startDate());
          assertThat(responseBody.endDate()).isEqualTo(rateExpected.endDate());
        });
  }

  @Test
  void getRateByProductIdAndBrandIdAndDate_ShouldReturn404_WhenRatesNotFound() {
    // Given
    String applicationDate = "2020-6-14 21:00";
    Integer productId = 35455;
    Integer brandId = 1;

    FindRateByProductIdAndApplicationDateAndBrandIdQuery query = FindRateByProductIdAndApplicationDateAndBrandIdQuery.builder()
        .applicationDate(applicationDate)
        .brandId(brandId)
        .productId(productId)
        .build();

    given(findRateByProductIdAndApplicationDateAndBrandIdQueryHandler.execute(query))
        .willThrow(new RateNotFoundException(productId, brandId, applicationDate));

    // When and Then
    testClient.get()
        .uri(String.format("/v1/rates?applicationDate=%s&productId=%d&brandId=%d",
            applicationDate, productId, brandId))
        .accept(APPLICATION_JSON)
        .exchange()
        .expectStatus().isNotFound()
        .expectBody()
        .jsonPath("$.message").isEqualTo(
            String.format("Not found rate with product id: '%s', brand id: '%s' and date: '%s'",
                productId, brandId, applicationDate)
        );
  }

}