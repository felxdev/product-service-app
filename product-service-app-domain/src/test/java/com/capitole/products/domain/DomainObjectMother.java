package com.capitole.products.domain;

import com.capitole.products.domain.model.Amount;
import com.capitole.products.domain.model.Rate;
import com.capitole.products.domain.model.Rate.RateBuilder;

public class DomainObjectMother {

  public static RateBuilder rateDefault() {
    return Rate.builder()
        .id("1")
        .brandId(1)
        .productId(35455)
        .startDate("2020-06-14 10:00:00")
        .endDate("2020-12-31 15:59:59")
        .priority("0")
        .appliedRate(1)
        .price(amountValue355Exponent1());
  }

  private static Amount amountValue355Exponent1() {
    return Amount.builder().value(355).exponent(1).currency("EUR").build();
  }

  public static Amount amountValue2888Exponent2() {
    return Amount.builder().value(2888).exponent(2).currency("EUR").build();
  }

}
