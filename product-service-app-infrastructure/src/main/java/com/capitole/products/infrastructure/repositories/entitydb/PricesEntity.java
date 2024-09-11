package com.capitole.products.infrastructure.repositories.entitydb;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.Data;

@Data
@Entity
@Table(name = "PRICES")
public class PricesEntity {

  @EmbeddedId
  private PricesId id;

  @Column(name = "BRAND_ID")
  private String brandId;

  @Column(name = "START_DATE")
  private LocalDate startDate;

  @Column(name = "END_DATE")
  private LocalDate endDate;

  @Column(name = "PRICE_LIST")
  private String priceList;

  @Column(name = "PRODUCT_ID")
  private String productId;

  @Column(name = "PRIORITY")
  private String priority;

  @Column(name = "PRICE")
  private Double price;

  @Column(name = "CURR")
  private String currency;
}
