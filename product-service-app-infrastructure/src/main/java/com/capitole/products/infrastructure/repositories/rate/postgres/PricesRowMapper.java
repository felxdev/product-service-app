package com.capitole.products.infrastructure.repositories.rate.postgres;

import com.capitole.products.domain.model.Amount;
import com.capitole.products.domain.model.Rate;
import com.capitole.products.infrastructure.repositories.rate.filters.FindRatesByFilter;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValueMappingStrategy;

@Mapper(collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
    nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
public interface PricesRowMapper {

  @Mapping(target = "price", source = ".")
  @Mapping(target = "appliedRate", source = "priceList")
  Rate asRate(PricesRow pricesRow);

  default Amount asPrice(PricesRow price) {
    return Amount.from(Double.toString(price.getPrice()), price.getCurrency());
  }

  default FindRatesByFilter asFindRatesByFilter(Integer productId, Integer brandId,
      String date) {

    return FindRatesByFilter.builder()
        .productId(productId)
        .brandId(brandId)
        .date(date)
        .build();
  }
}
