package com.capitole.products.domain.exceptions;

import static com.capitole.products.domain.exceptions.ApplicationExceptionCode.RATE_NOT_FOUND_EXCEPTION;

public class RateNotFoundException extends ApplicationException {

  public RateNotFoundException(String id) {
    super(RATE_NOT_FOUND_EXCEPTION, String.format("Not found rate by product id: '%s'", id));
  }
}
