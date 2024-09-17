package com.capitole.products.domain.exceptions;

import static com.capitole.products.domain.exceptions.ApplicationExceptionCode.RATE_NOT_FOUND_EXCEPTION;

public class RateNotFoundException extends ApplicationException {

  public RateNotFoundException(Integer productId, Integer brandId, String date) {
    super(RATE_NOT_FOUND_EXCEPTION,
        String.format("Not found rate with product id: '%d', brand id: '%d' and date: '%s'",
            productId, brandId, date));
  }
}
