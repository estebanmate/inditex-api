package com.inditex.challenge.application.rest.controller;

import static org.assertj.core.api.Assertions.assertThat;

import com.inditex.challenge.application.request.PriceRequest;
import com.inditex.challenge.application.response.PriceResponse;
import com.inditex.challenge.application.rest.exception.type.InvalidFieldException;
import com.inditex.challenge.application.rest.exception.type.MissingFieldException;
import com.inditex.challenge.application.rest.resources.ExceptionCodes;
import com.inditex.challenge.application.rest.resources.PriceApiPaths;
import jakarta.inject.Inject;
import java.util.Objects;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class PriceControllerTest {

  private static final String API_PRICES_URL = PriceApiPaths.BASE_PATH
      .concat(PriceApiPaths.GET_FILTER_PRICE);

  private static final Long BRAND_ID_TEST = 1L;
  private static final Long PRODUCT_ID_TEST = 35455L;

  private static final String DATETIME_WITH_DATA = "2020-08-12 12:00:00";

  private static final String DATETIME_WITHOUT_DATA = "2021-06-12 12:00:00";

  private static final String INVALID_DATETIME = "12-08-2020 12:00:00";

  @Inject
  private TestRestTemplate testRestTemplate;

  private static Stream<Arguments> provideTimestampAndExpectedPrice() {
    return Stream.of(
        Arguments.of(
            "2020-06-14 10:00:00", 35.50),
        Arguments.of(
            "2020-06-14 16:00:00", 25.45),
        Arguments.of(
            "2020-06-14 21:00:00", 35.50),
        Arguments.of(
            "2020-06-15 10:00:00", 30.50),
        Arguments.of(
            "2020-06-16 21:00:00", 38.95)
    );
  }

  @Test
  void should_get_price_when_request_ok_and_exists_data() {

    ResponseEntity<PriceResponse> getPriceResponse = performPriceEndpointCall(new HttpEntity<>(
        createPriceRequest(DATETIME_WITH_DATA)), PriceResponse.class);

    assertThat(getPriceResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(Objects.requireNonNull(getPriceResponse.getBody()).getBrandId()).isEqualTo(1L);
  }

  @Test
  void should_get_not_found_error_when_request_ok_and_not_exists_data() {

    ResponseEntity<PriceResponse> getPriceResponse = performPriceEndpointCall(new HttpEntity<>(
        createPriceRequest(DATETIME_WITHOUT_DATA)), PriceResponse.class);

    assertThat(getPriceResponse.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    assertThat(Objects.requireNonNull(getPriceResponse.getBody()).getBrandId()).isNull();

  }

  @Test
  void should_get_missing_field_error_when_request_has_missing_mandatory_fields() {

    ResponseEntity<MissingFieldException> errorResponse = performPriceEndpointCall(new HttpEntity<>(
        createPriceRequest(null)), MissingFieldException.class);

    assertThat(errorResponse.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    assertThat(Objects.requireNonNull(errorResponse.getBody()).getErrorCode()).isEqualTo(ExceptionCodes.MISSING_FIELD);

  }

  @Test
  void should_get_invalid_field_error_when_request_field_has_invalid_value_or_format() {

    ResponseEntity<InvalidFieldException> errorResponse = performPriceEndpointCall(new HttpEntity<>(
        createPriceRequest(INVALID_DATETIME)), InvalidFieldException.class);

    assertThat(errorResponse.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    assertThat(Objects.requireNonNull(errorResponse.getBody()).getErrorCode()).isEqualTo(ExceptionCodes.INVALID_FIELD_VALUE);

  }

  @ParameterizedTest(name = "Should get the price that applies in requested date and time {index}")
  @MethodSource("provideTimestampAndExpectedPrice")
  void should_get_price_with_request_at_request_date_and_time(String dateTime, Double expectedResult) {

    ResponseEntity<PriceResponse> getPriceResponse = performPriceEndpointCall(new HttpEntity<>(
        createPriceRequest(dateTime)), PriceResponse.class);

    assertThat(getPriceResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(Objects.requireNonNull(getPriceResponse.getBody()).getPrice()).isEqualTo(expectedResult);
  }

  private PriceRequest createPriceRequest(String date) {

    return PriceRequest.builder()
        .brandId(BRAND_ID_TEST)
        .productId(PRODUCT_ID_TEST)
        .priceDate(date)
        .build();
  }

  private <T> ResponseEntity<T> performPriceEndpointCall(HttpEntity<PriceRequest> findPriceFilterRequestHttpEntity,
      Class<T> responseClass) {
    return testRestTemplate
        .exchange(API_PRICES_URL, HttpMethod.POST, findPriceFilterRequestHttpEntity, responseClass);
  }
}
