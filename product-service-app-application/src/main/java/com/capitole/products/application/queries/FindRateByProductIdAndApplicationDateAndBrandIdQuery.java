package com.capitole.products.application.queries;

import lombok.Builder;

@Builder
public record FindRateByProductIdAndApplicationDateAndBrandIdQuery(
    String productId,
    String applicationDate,
    Integer brandId
) implements Query {

}
