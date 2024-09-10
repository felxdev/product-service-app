package com.capitole.products.domain.model;

import lombok.Builder;

@Builder
public record Amount(
    Integer value,
    String currency,
    Integer exponent
) {

}
