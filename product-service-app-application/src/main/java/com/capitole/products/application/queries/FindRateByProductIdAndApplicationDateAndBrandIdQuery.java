package com.capitole.products.application.queries;

import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record FindRateByProductIdAndApplicationDateAndBrandIdQuery(
    String productId,
    LocalDateTime applicationDate,
    String brandId
) implements Query {

}
