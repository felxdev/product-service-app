package com.capitole.products.infrastructure.repositories.entitydb;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
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
  private LocalDateTime startDate;

  @Column(name = "END_DATE")
  private LocalDateTime endDate;

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

  public PricesEntity(PricesId id, String brandId, LocalDateTime startDate, LocalDateTime endDate,
      String priceList, String productId, String priority, Double price, String currency) {
    this.id = id;
    this.brandId = brandId;
    this.startDate = startDate;
    this.endDate = endDate;
    this.priceList = priceList;
    this.productId = productId;
    this.priority = priority;
    this.price = price;
    this.currency = currency;
  }

  public PricesEntity() {

  }
}
