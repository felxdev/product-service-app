package com.capitole.products.infrastructure.repositories.rate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import com.capitole.products.domain.DomainObjectMother;
import com.capitole.products.domain.model.Rate;
import com.capitole.products.infrastructure.repositories.rate.postgres.PricesObjectMother;
import com.capitole.products.infrastructure.repositories.rate.postgres.PricesRepositoryR2dbc;
import com.capitole.products.infrastructure.repositories.rate.postgres.PricesRow;
import com.capitole.products.infrastructure.repositories.rate.postgres.PricesRowMapperImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
class RateRepositoryImplTest {

  @Mock
  PricesRepositoryR2dbc pricesRepositoryR2dbc;

  @Spy
  PricesRowMapperImpl pricesRowMapper;

  @InjectMocks
  RateRepositoryImpl rateRepositoryImpl;

  @Test
  void findByProductIdAndBrandIdAndDate() {

    // Given
    String dateGiven = "2025-03-24 10:30:00";
    PricesRow pricesRow = PricesObjectMother.pricesRowDefault();
    Rate rateExpected = DomainObjectMother.rateDefault()
        .startDate("2025-03-24 10:00:00")
        .endDate("2025-03-24 11:00:00")
        .priority("1")
        .build();

    given(
        pricesRepositoryR2dbc.findByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
            35455, 1, dateGiven, dateGiven)
    ).willReturn(Flux.just(pricesRow));

    // When
    StepVerifier.create(
            rateRepositoryImpl.findByProductIdAndBrandIdAndDate(35455, 1, dateGiven))
        .assertNext(rate -> {
          // Then
          assertThat(rate).isNotNull();
          assertThat(rate).isEqualTo(rateExpected);
        })
        .verifyComplete();
  }
}