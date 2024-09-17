package com.capitole.products.infrastructure.repositories.rate.postgres;

import java.util.List;
import reactor.core.publisher.Flux;

public class PricesObjectMother {

  public static PricesRow pricesRowDefault() {
    return new PricesRow(
        1L,
        1,
        "2025-03-24 10:00:00",
        "2025-03-24 11:00:00",
        1,
        35455,
        1,
        35.5,
        "EUR");
  }

  public static List<PricesRow> pricesRowsList() {
    return List.of(
        new PricesRow(1L, 1, "2025-03-24 10:00:00", "2025-03-24 11:00:00", 1, 35455, 1, 35.5,
            "EUR"),
        new PricesRow(2L, 1, "2025-03-24 16:00:00", "2025-03-24 21:00:00", 2, 35455, 1, 25.45,
            "EUR"),
        new PricesRow(3L, 1, "2025-03-24 00:00:00", "2025-03-24 23:59:59", 3, 35455, 1, 30.5,
            "EUR"),
        new PricesRow(4L, 1, "2025-03-24 00:00:00", "2025-03-24 23:59:59", 4, 35455, 1, 38.95,
            "EUR")
    );
  }

  public static Flux<PricesRow> pricesRowFlux() {
    return Flux.fromIterable(pricesRowsList());
  }
}
