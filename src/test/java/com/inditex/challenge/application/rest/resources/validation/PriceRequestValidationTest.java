package com.inditex.challenge.application.rest.resources.validation;

import com.inditex.challenge.application.request.PriceRequest;
import com.inditex.challenge.application.request.PriceRequestValidation;
import com.inditex.challenge.application.rest.exception.type.InvalidFieldException;
import com.inditex.challenge.application.rest.exception.type.MissingFieldException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PriceRequestValidationTest {

    @Test
    public void testValidatePriceFilterRequest_missingProductId() {
        PriceRequest priceRequest = PriceRequest.builder().build();
        assertThrows(MissingFieldException.class, () -> PriceRequestValidation.validatePriceFilterRequest(priceRequest));
    }

    @Test
    public void testValidatePriceFilterRequest_invalidProductId() {
        PriceRequest priceRequest = PriceRequest.builder().productId(0L).build();
        assertThrows(InvalidFieldException.class, () -> PriceRequestValidation.validatePriceFilterRequest(priceRequest));
    }

    @Test
    public void testValidatePriceFilterRequest_missingBrandId() {
        PriceRequest priceRequest = PriceRequest.builder().productId(1L).build();
        assertThrows(MissingFieldException.class, () -> PriceRequestValidation.validatePriceFilterRequest(priceRequest));
    }

    @Test
    public void testValidatePriceFilterRequest_invalidBrandId() {
        PriceRequest priceRequest = PriceRequest.builder().productId(1L).brandId(0L).build();
        assertThrows(InvalidFieldException.class, () -> PriceRequestValidation.validatePriceFilterRequest(priceRequest));
    }

    @Test
    public void testValidatePriceFilterRequest_missingPriceDate() {
        PriceRequest priceRequest = PriceRequest.builder().productId(1L).brandId(1L).build();
        assertThrows(MissingFieldException.class, () -> PriceRequestValidation.validatePriceFilterRequest(priceRequest));
    }

    @Test
    public void testValidatePriceFilterRequest_invalidPriceDate() {
        PriceRequest priceRequest = PriceRequest.builder().productId(1L).brandId(1L).priceDate("invalid_date").build();
        assertThrows(InvalidFieldException.class, () -> PriceRequestValidation.validatePriceFilterRequest(priceRequest));
    }

    @Test
    public void testValidatePriceFilterRequest_validRequest() {
        PriceRequest priceRequest = PriceRequest.builder().productId(1L).brandId(1L).priceDate("2020-11-20 10:00:00").build();
        assertDoesNotThrow(() -> PriceRequestValidation.validatePriceFilterRequest(priceRequest));
    }
}