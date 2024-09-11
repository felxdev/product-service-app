package com.capitole.products.domain.model;

import lombok.Builder;

@Builder
public record Amount(
    Integer value,
    String currency,
    Integer exponent
) {

  private static final String DEFAULT_CURRENCY_SEPARATOR = ".";

  public static Amount from(String value, String currency) {

    if (value.length() > 9) {
      value = value.substring(0, 9);
    }

    return Amount.builder()
        .value(getInt(value))
        .exponent(calculateExponent(value))
        .currency(currency)
        .build();
  }

  private static int calculateExponent(String value) {
    return value.length() - (value.indexOf(DEFAULT_CURRENCY_SEPARATOR) + 1);
  }

  private static int getInt(String value) {
    return Integer.parseInt(value.replace(DEFAULT_CURRENCY_SEPARATOR, ""));
  }
}
