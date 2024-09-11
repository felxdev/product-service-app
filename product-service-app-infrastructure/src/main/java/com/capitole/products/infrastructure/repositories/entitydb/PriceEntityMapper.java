package com.capitole.products.infrastructure.repositories.entitydb;

import com.capitole.products.domain.model.Rate;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValueMappingStrategy;

@Mapper(collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
    nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
public interface PriceEntityMapper {

  @Mapping(target = "amount.value", source = "price")
  @Mapping(target = "amount.currency", constant = "currency")
  Rate asRate(PricesEntity pricesEntity);

}
