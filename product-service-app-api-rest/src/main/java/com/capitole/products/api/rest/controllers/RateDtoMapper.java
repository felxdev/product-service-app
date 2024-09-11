package com.capitole.products.api.rest.controllers;

import com.capitole.products.api.rest.dtos.AmountDto;
import com.capitole.products.api.rest.dtos.RateInfoDto;
import com.capitole.products.application.queries.FindRateByProductIdAndApplicationDateAndBrandIdQuery;
import com.capitole.products.domain.model.Amount;
import com.capitole.products.domain.model.Rate;
import java.time.LocalDate;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValueMappingStrategy;

@Mapper(collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
    nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
public interface RateDtoMapper {

  FindRateByProductIdAndApplicationDateAndBrandIdQuery asFindRateByProductIdAndApplicationDateAndBrandIdQuery(
      Integer productId, LocalDate applicationDate, Integer brandId);

  @Mapping(target = "appliedRateId", source = "appliedRate")
  RateInfoDto asRateInfoDto(Rate rate);

  AmountDto asAmountDto(Amount amount);
}
