package com.inditex.challenge.application.rest.mapper;

import com.inditex.challenge.application.response.PriceResponse;
import com.inditex.challenge.domain.entities.Price;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PriceMapper {
  PriceResponse activePriceToResponse(Price activePrice);
}
