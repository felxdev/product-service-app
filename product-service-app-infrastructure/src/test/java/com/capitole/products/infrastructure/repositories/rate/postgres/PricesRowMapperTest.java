package com.capitole.products.infrastructure.repositories.rate.postgres;

import static com.capitole.products.infrastructure.repositories.rate.postgres.PricesObjectMother.pricesRowDefault;
import static org.assertj.core.api.Assertions.assertThat;

import com.capitole.products.domain.model.Amount;
import com.capitole.products.domain.model.Rate;
import com.capitole.products.infrastructure.repositories.rate.filters.FindRatesByFilter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PricesRowMapperTest {

  PricesRowMapper mapper = Mappers.getMapper(PricesRowMapper.class);

  @Test
  void asRate_shouldMapFromPricesEntityToRate_OnSuccess() {

    // Given
    PricesRow pricesRow = pricesRowDefault();
    Rate expectedRate = Rate.builder()
        .id("1")
        .productId(35455)
        .brandId(1)
        .priority("1")
        .startDate("2025-03-24 10:00:00")
        .endDate("2025-03-24 11:00:00")
        .price(Amount.builder().value(355).currency("EUR").exponent(1).build())
        .appliedRate(1)
        .build();

    // When
    Rate actual = mapper.asRate(pricesRow);

    // Then
    assertThat(actual).usingRecursiveComparison().isEqualTo(expectedRate);
  }

  @Test
  void asFindRatesByFilter() {

    // Given
    Integer pId = 35455;
    Integer brandId = 1;
    String date = "2025-03-24 10:00:00";

    String expectedDate = "2025-03-24 10:00:00";

    // When
    FindRatesByFilter actual = mapper.asFindRatesByFilter(pId, brandId, date);

    // Then
    assertThat(actual.productId()).isEqualTo(35455);
    assertThat(actual.brandId()).isEqualTo(1);
    assertThat(actual.date()).isEqualTo(expectedDate);
  }
}