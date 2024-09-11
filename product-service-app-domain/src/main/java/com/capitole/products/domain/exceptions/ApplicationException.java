package com.capitole.products.domain.exceptions;

import lombok.Getter;

@Getter
public class ApplicationException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  private final ApplicationExceptionCode applicationExceptionCode;

  public ApplicationException(ApplicationExceptionCode applicationExceptionCode, String message) {
    super(message);
    this.applicationExceptionCode = applicationExceptionCode;
  }
}
