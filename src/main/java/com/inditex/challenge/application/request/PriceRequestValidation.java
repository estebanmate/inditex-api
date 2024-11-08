package com.inditex.challenge.application.request;

import static java.util.Objects.isNull;
import static org.apache.commons.lang3.StringUtils.isBlank;

import com.inditex.challenge.application.rest.exception.type.InvalidFieldException;
import com.inditex.challenge.application.rest.exception.type.MissingFieldException;
import com.inditex.challenge.application.rest.resources.ErrorMessages;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PriceRequestValidation {

  private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

  @SuppressWarnings("unused")
  public static void validatePriceFilterRequest(PriceRequest priceRequest)
          throws InvalidFieldException, MissingFieldException {

    // Check productId presence and value
    if (isNull(priceRequest.getProductId())) {
      log.error(ErrorMessages.MISSING_FIELD_ERROR_MESSAGE, "PRODUCT_ID");
      throw new MissingFieldException("PRODUCT_ID");
    } else if (priceRequest.getProductId() < 1) {
      log.error(ErrorMessages.INVALID_FIELD_ERROR_MESSAGE, "PRODUCT_ID");
      throw new InvalidFieldException("PRODUCT_ID");
    }

    // Check brandId presence and value
    if (isNull(priceRequest.getBrandId())) {
      log.error(ErrorMessages.MISSING_FIELD_ERROR_MESSAGE, "BRAND_ID");
      throw new MissingFieldException("BRAND_ID");
    } else if (priceRequest.getBrandId() < 1) {
      log.error(ErrorMessages.INVALID_FIELD_ERROR_MESSAGE, "BRAND_ID");
      throw new InvalidFieldException("BRAND_ID");
    }

    // Check date presence and value
    if (isBlank(priceRequest.getPriceDate())) {
      log.error(ErrorMessages.MISSING_FIELD_ERROR_MESSAGE, "PRICE_DATE");
      throw new MissingFieldException("PRICE_DATE");
    } else {
      try {
        LocalDateTime.parse(priceRequest.getPriceDate(), dateTimeFormatter);
      } catch (DateTimeParseException ex) {
        log.error(ErrorMessages.INVALID_FIELD_ERROR_MESSAGE, "PRICE_DATE");
        throw new InvalidFieldException("PRICE_DATE");
      }
    }

  }
}
