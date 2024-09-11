package com.capitole.products.api.rest.controllers;

import static org.assertj.core.api.Assertions.assertThat;

import com.capitole.products.api.rest.dtos.AmountDto;
import com.capitole.products.api.rest.dtos.RateInfoDto;
import com.capitole.products.application.queries.FindRateByProductIdAndApplicationDateAndBrandIdQuery;
import com.capitole.products.domain.model.Amount;
import com.capitole.products.domain.model.Rate;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RateDtoMapperTest {

  RateDtoMapper mapper = Mappers.getMapper(RateDtoMapper.class);

  @Test
  void asFindRateByProductIdAndApplicationDateAndBrandIdQuery_ShouldMap_OnSucess() {
    // Given
    FindRateByProductIdAndApplicationDateAndBrandIdQuery expected = FindRateByProductIdAndApplicationDateAndBrandIdQuery.builder()
        .productId("35455")
        .applicationDate(LocalDate.of(2025, 3, 24))
        .brandId("1")
        .build();
    // When
    FindRateByProductIdAndApplicationDateAndBrandIdQuery actual = mapper.asFindRateByProductIdAndApplicationDateAndBrandIdQuery(
        35455, LocalDate.of(2025, 3, 24), 1);

    // Then
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  void asRateInfoDto_ShouldMapFromRateToRateInfoDto() {
    // Given
    AmountDto amountDto = new AmountDto();
    amountDto.setValue(355L);
    amountDto.setCurrency("EUR");

    Rate rate = Rate.builder()
        .productId("35455")
        .brandId("1")
        .startDate(LocalDate.of(2025, 6, 14))
        .endDate(LocalDate.of(2025, 6, 14))
        .price(Amount.builder()
            .value(355)
            .currency("EUR")
            .build())
        .appliedRate(1)
        .build();

    RateInfoDto expected = new RateInfoDto();
    expected.setProductId("35455");
    expected.setBrandId(1);
    expected.setStartDate(LocalDate.of(2025, 6, 14));
    expected.setEndDate(LocalDate.of(2025, 6, 14));
    expected.setPrice(amountDto);
    expected.appliedRateId(1);

    // When
    RateInfoDto actual = mapper.asRateInfoDto(rate);

    // Then
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  void asAmountDto_ShouldMapFromAmountToDto_Onsuccess() {
    // Given
    Amount amount = Amount.builder()
        .value(355)
        .currency("EUR")
        .exponent(1)
        .build();

    AmountDto expected = new AmountDto();
    expected.setValue(355L);
    expected.setCurrency("EUR");
    expected.setExponent(1);

    // When
    AmountDto actual = mapper.asAmountDto(amount);

    // Then
    assertThat(actual).isEqualTo(expected);
  }
}