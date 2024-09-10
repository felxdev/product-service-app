package com.capitole.products.domain.exceptions;

import lombok.Getter;

public enum ApplicationExceptionCode {
  RATE_NOT_FOUND_EXCEPTION(1);

  @Getter
  private final int errorCode;

  ApplicationExceptionCode(int errorCode) {
    this.errorCode = errorCode;
  }
}
