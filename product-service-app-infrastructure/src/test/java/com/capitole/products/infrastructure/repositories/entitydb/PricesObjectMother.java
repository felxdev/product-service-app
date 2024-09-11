package com.capitole.products.infrastructure.repositories.entitydb;

import java.time.LocalDate;

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

  private static LocalDate startDate() {
    return LocalDate.of(2025, 3, 24);
  }

  private static LocalDate endDate() {
    return LocalDate.of(2025, 3, 24);
  }
}
