package com.capitole.products.api.rest.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;

import com.capitole.products.application.queries.FindRateByProductIdAndApplicationDateAndBrandIdQuery;
import com.capitole.products.application.queries.FindRateByProductIdAndApplicationDateAndBrandIdQueryHandler;
import com.capitole.products.domain.exceptions.RateNotFoundException;
import com.capitole.products.domain.model.Rate;
import java.time.LocalDateTime;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@WebFluxTest
@ContextConfiguration(classes = {RatesApiController.class})
class RatesApiControllerTest {

  @MockBean
  private FindRateByProductIdAndApplicationDateAndBrandIdQueryHandler findRateByProductIdAndApplicationDateAndBrandIdQueryHandler;

  @Spy
  private RateDtoMapper rateDtoMapper;

  @Autowired
  private WebTestClient testClient;

  @ParameterizedTest
  @MethodSource("provideTestCases")
  void getRateByProductIdAndBrandIdAndDate_ShouldReturnRate_OnSuccess(LocalDateTime applicationDate,
      String productId, String brandId) {
    // Given
    Rate rate = Rate.builder()
        .brandId(brandId)
        .productId(productId)
        .startDate(applicationDate)
        .build();

    FindRateByProductIdAndApplicationDateAndBrandIdQuery query = FindRateByProductIdAndApplicationDateAndBrandIdQuery.builder()
        .applicationDate(applicationDate)
        .brandId(brandId)
        .productId(productId)
        .build();

    Mono<Rate> monoRate = Mono.just(rate);

    given(findRateByProductIdAndApplicationDateAndBrandIdQueryHandler.execute(query))
        .willReturn(monoRate);

    // When and Then
    testClient.get()
        .uri(String.format("/v1/rates?applicationDate=%s&productId=%s&brandId=%s",
            applicationDate.toString().replace("T", "-"), productId, brandId))
        .accept(APPLICATION_JSON)
        .exchange()
        .expectStatus().isOk()
        .expectHeader().contentType(APPLICATION_JSON)
        .expectBody(Rate.class)
        .consumeWith(response -> {
          Rate responseBody = response.getResponseBody();
          assertThat(responseBody).isNotNull();
        });
  }

  private static Stream<Arguments> provideTestCases() {
    return Stream.of(
        Arguments.of(LocalDateTime.of(2020, 6, 14, 10, 0), "35455", "1"),
        Arguments.of(LocalDateTime.of(2020, 6, 14, 16, 0), "35455", "1"),
        Arguments.of(LocalDateTime.of(2020, 6, 15, 10, 0), "35455", "1"),
        Arguments.of(LocalDateTime.of(2020, 6, 16, 21, 0), "35455", "1")
    );
  }

  @Test
  void getRateByProductIdAndBrandIdAndDate_ShouldThrowRateNotFoundException() {
    // Given
    LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 21, 0);
    String productId = "35455";
    String brandId = "1";

    FindRateByProductIdAndApplicationDateAndBrandIdQuery query = FindRateByProductIdAndApplicationDateAndBrandIdQuery.builder()
        .applicationDate(applicationDate)
        .brandId(brandId)
        .productId(productId)
        .build();

    given(findRateByProductIdAndApplicationDateAndBrandIdQueryHandler.execute(query))
        .willThrow(new RateNotFoundException(productId, brandId, applicationDate));

    // When and Then
    testClient.get()
        .uri(String.format("/v1/rates?applicationDate=%s&productId=%s&brandId=%s",
            applicationDate.toString().replace("T", "-"), productId, brandId))
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