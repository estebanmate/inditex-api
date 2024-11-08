package com.inditex.challenge.application.rest.controller;

import com.inditex.challenge.application.request.PriceRequest;
import com.inditex.challenge.application.response.PriceResponse;
import com.inditex.challenge.application.rest.exception.type.BaseException;
import com.inditex.challenge.application.rest.resources.PriceApiPaths;
import com.inditex.challenge.domain.service.PriceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

@RequestMapping(PriceApiPaths.BASE_PATH)
@RestController
public class PriceController {

  @Inject
  PriceService priceService;

  @PostMapping(
      value = PriceApiPaths.GET_FILTER_PRICE,
      consumes = {MediaType.APPLICATION_JSON_VALUE},
      produces = {MediaType.APPLICATION_JSON_VALUE}
  )
  @Operation(summary = "Find Price", description = "Find Price Filtered By Product Id, Brand Id and Date")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successfully retrieved price data", content = {
          @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = PriceResponse.class)))}),
      @ApiResponse(responseCode = "400", description = "Missing or invalid field value"),
      @ApiResponse(responseCode = "404", description = "Price data not found with request parameters", content = @Content)})
  public ResponseEntity<PriceResponse> findPriceByFilter(
      @Valid @RequestBody PriceRequest priceRequest)
      throws BaseException {

    return ResponseEntity.ok(priceService.findPrice(priceRequest));

  }
}
