package com.capitole.products.infrastructure.repositories.entitydb;

import java.time.LocalDateTime;

public class PricesObjectMother {

  public static PricesEntity priceEntityDefault() {
    return new PricesEntity(
        pricesIdDefault(),
        "1",
        startDate(),
        endDate(),
        "1",
        "35455",
        "1",
        35.5,
        "EUR");
  }

  private static PricesId pricesIdDefault() {
    return new PricesId("1", startDate(), "1", "35455");
  }

  private static LocalDateTime startDate() {
    return LocalDateTime.of(2025, 3, 24, 10, 0);
  }

  private static LocalDateTime endDate() {
    return LocalDateTime.of(2025, 3, 24, 10, 0);
  }
}
