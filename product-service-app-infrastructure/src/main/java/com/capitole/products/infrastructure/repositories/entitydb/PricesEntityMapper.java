package com.capitole.products.infrastructure.repositories.entitydb;

import com.capitole.products.domain.model.Amount;
import com.capitole.products.domain.model.Rate;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValueMappingStrategy;

@Mapper(collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
    nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
public interface PricesEntityMapper {

  @Mapping(target = "price", source = ".")
  @Mapping(target = "appliedRate", source = "priceList")
  Rate asRate(PricesEntity pricesEntity);

  default Amount asPrice(PricesEntity price) {
    return Amount.from(Double.toString(price.getPrice()), price.getCurrency());
  }
}
