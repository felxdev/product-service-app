package com.capitole.products.api.rest.controllers;

import com.capitole.products.domain.model.Amount;
import com.capitole.products.domain.model.Rate;
import com.capitole.products.domain.model.Rate.RateBuilder;
import java.time.LocalDate;

public class RestObjectMother {

  public static RateBuilder rateDefault() {
    return Rate.builder()
        .productId("35455")
        .brandId("1")
        .priority("1")
        .startDate(LocalDate.of(2025, 3, 24))
        .endDate(LocalDate.of(2025, 3, 24))
        .price(Amount.builder().value(355).currency("EUR").exponent(1).build())
        .appliedRate(1);
  }
}
