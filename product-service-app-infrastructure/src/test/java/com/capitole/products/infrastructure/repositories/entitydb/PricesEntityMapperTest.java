package com.capitole.products.infrastructure.repositories.entitydb;

import static com.capitole.products.infrastructure.repositories.entitydb.PricesObjectMother.priceEntityDefault;
import static org.assertj.core.api.Assertions.assertThat;

import com.capitole.products.domain.model.Amount;
import com.capitole.products.domain.model.Rate;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PricesEntityMapperTest {

  PricesEntityMapper mapper = Mappers.getMapper(PricesEntityMapper.class);

  @Test
  void asRate_shouldMapFromPricesEntityToRate_OnSuccess() {

    // Given
    PricesEntity pricesEntity = priceEntityDefault();
    Rate expectedRate = Rate.builder()
        .productId("35455")
        .brandId("1")
        .priority("1")
        .startDate(LocalDateTime.of(2025, 3, 24, 10, 0))
        .endDate(LocalDateTime.of(2025, 3, 24, 10, 0))
        .price(Amount.builder().value(355).currency("EUR").exponent(1).build())
        .appliedRate(1)
        .build();

    // When
    Rate actual = mapper.asRate(pricesEntity);

    // Then
    assertThat(actual).usingRecursiveComparison().isEqualTo(expectedRate);
  }

}