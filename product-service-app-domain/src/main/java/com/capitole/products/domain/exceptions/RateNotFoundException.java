package com.capitole.products.domain.exceptions;

import static com.capitole.products.domain.exceptions.ApplicationExceptionCode.RATE_NOT_FOUND_EXCEPTION;

import java.time.LocalDate;

public class RateNotFoundException extends ApplicationException {

  public RateNotFoundException(String productId, String brandId, LocalDate date) {
    super(RATE_NOT_FOUND_EXCEPTION,
        String.format("Not found rate with product id: '%s', brand id: '%s' and date: '%s'",
            productId, brandId, date));
  }
}
