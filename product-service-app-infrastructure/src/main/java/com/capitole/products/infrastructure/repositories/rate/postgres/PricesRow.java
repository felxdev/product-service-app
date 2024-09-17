package com.capitole.products.infrastructure.repositories.rate.postgres;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("MYSCHEMA.PRICES")
public class PricesRow {

  @Id
  @Column("ID")
  private Long id;

  @Column("BRAND_ID")
  private Integer brandId;

  @Column("START_DATE")
  private String startDate;

  @Column("END_DATE")
  private String endDate;

  @Column("PRICE_LIST")
  private Integer priceList;

  @Column("PRODUCT_ID")
  private Integer productId;

  @Column("PRIORITY")
  private Integer priority;

  @Column("PRICE")
  private Double price;

  @Column("CURR")
  private String currency;
}
