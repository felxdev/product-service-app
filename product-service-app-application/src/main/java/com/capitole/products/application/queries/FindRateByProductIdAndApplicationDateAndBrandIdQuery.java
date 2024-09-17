package com.capitole.products.application.queries;

import lombok.Builder;

@Builder
public record FindRateByProductIdAndApplicationDateAndBrandIdQuery(
    Integer productId,
    String applicationDate,
    Integer brandId
) implements Query {

}
