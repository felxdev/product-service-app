package com.capitole.products.infrastructure.repositories.entitydb;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

@Data
@Embeddable
public class PricesId implements Serializable {

  @Column(name = "BRAND_ID")
  private String brandId;

  @Column(name = "START_DATE")
  private LocalDateTime startDate;

  @Column(name = "PRICE_LIST")
  private String priceList;

  @Column(name = "PRODUCT_ID")
  private String productId;

}
