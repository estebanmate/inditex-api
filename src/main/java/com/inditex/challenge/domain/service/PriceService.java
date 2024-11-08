package com.inditex.challenge.domain.service;

import com.inditex.challenge.application.request.PriceRequest;
import com.inditex.challenge.application.response.PriceResponse;
import com.inditex.challenge.application.rest.exception.type.BaseException;

public interface PriceService {

  PriceResponse findPrice(PriceRequest priceRequest) throws BaseException;

}
