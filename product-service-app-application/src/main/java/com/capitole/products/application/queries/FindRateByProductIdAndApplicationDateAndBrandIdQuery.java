package com.capitole.products.application.queries;

import java.time.LocalDate;
import lombok.Builder;

@Builder
public record FindRateByProductIdAndApplicationDateAndBrandIdQuery(
    String productId,
    LocalDate applicationDate,
    String brandId
) implements Query {

}
