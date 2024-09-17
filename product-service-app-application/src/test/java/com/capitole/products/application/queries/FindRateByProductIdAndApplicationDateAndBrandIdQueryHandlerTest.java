package com.capitole.products.application.queries;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import com.capitole.products.domain.DomainObjectMother;
import com.capitole.products.domain.exceptions.RateNotFoundException;
import com.capitole.products.domain.model.Rate;
import com.capitole.products.domain.repositories.RateRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
class FindRateByProductIdAndApplicationDateAndBrandIdQueryHandlerTest {

  @Mock
  RateRepository rateRepository;

  @InjectMocks
  FindRateByProductIdAndApplicationDateAndBrandIdQueryHandler queryHandler;

  @Test
  void execute_ShouldReturnRateWithHigherPriority_WhenTwoRateAreFound() {

    // Given
    FindRateByProductIdAndApplicationDateAndBrandIdQuery query = QueryObjectMother
        .findRateByProductIdAndApplicationDateAndBrandIdQueryDefault();

    Rate ratePriority0 = DomainObjectMother.rateDefault().build();

    Rate ratePriority1 = DomainObjectMother.rateDefault()
        .priority("1")
        .price(DomainObjectMother.amountValue2888Exponent2())
        .build();

    Flux<Rate> rates = Flux.just(ratePriority0, ratePriority1);

    given(rateRepository.findByProductIdAndBrandIdAndDate(
        35455, 1, "2020-06-14 11:00:00")).willReturn(rates);

    // When
    Mono<Rate> execute = queryHandler.execute(query);

    // Then
    StepVerifier.create(execute)
        .assertNext(rate -> {
          assertThat(rate).isNotNull();
          assertThat(rate).isEqualTo(ratePriority1);
        })
        .verifyComplete();

  }

  @Test
  void execute_ShouldReturnRateNotFound_WhenTwoThereAreNotRatesFound() {

    // Given
    FindRateByProductIdAndApplicationDateAndBrandIdQuery query = QueryObjectMother
        .findRateByProductIdAndApplicationDateAndBrandIdQueryDefault();

    given(rateRepository.findByProductIdAndBrandIdAndDate(
        35455, 1, "2020-06-14 11:00:00")).willReturn(Flux.empty());

    // When
    Mono<Rate> execute = queryHandler.execute(query);

    // Then
    StepVerifier.create(execute)
        .expectError(RateNotFoundException.class)
        .verify();
  }
}