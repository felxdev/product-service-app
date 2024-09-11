package com.capitole.products.application.queries;

import java.time.LocalDate;
import lombok.Builder;

@Builder
public record FindRateByProductIdAndApplicationDateAndBrandIdQuery(
    Integer productId,
    LocalDate applicationDate,
    Integer brandId
) implements Query {

}
