Feature: Price API Test

  Background:
    * url 'http://localhost:8080/api/v1'
    * def ExceptionCodes = Java.type('com.inditex.challenge.application.rest.resources.ExceptionCodes')
    * def BRAND_ID_TEST = 1
    * def PRODUCT_ID_TEST = 35455
    * def DATETIME_WITH_DATA = '2020-06-14 10:00:00'
    * def DATETIME_WITHOUT_DATA = '2021-06-14 10:00:00'
    * def INVALID_DATETIME = 'invalid-datetime-string'
    * def INVALID_FIELD_VALUE = ExceptionCodes.INVALID_FIELD_VALUE
    * def MISSING_FIELD = ExceptionCodes.MISSING_FIELD

  Scenario: should_get_price_when_request_ok_and_exists_data
    Given path 'price'
    And request { brandId: '#(BRAND_ID_TEST)', productId: '#(PRODUCT_ID_TEST)', priceDate: '#(DATETIME_WITH_DATA)' }
    When method post
    Then status 200
    And assert response.brandId == 1L

  Scenario: should_get_not_found_error_when_request_ok_and_not_exists_data
    Given path 'price'
    And request { brandId: '#(BRAND_ID_TEST)', productId: '#(PRODUCT_ID_TEST)', priceDate: '#(DATETIME_WITHOUT_DATA)' }
    When method post
    Then status 404
    And assert response.brandId == null

  Scenario: should_get_missing_field_error_when_request_has_missing_mandatory_fields
    Given path 'price'
    And request { brandId: '#(BRAND_ID_TEST)', productId: '#(PRODUCT_ID_TEST)', priceDate: null }
    When method post
    Then status 400
    And assert response.errorCode == '#(MISSING_FIELD)'

  Scenario: should_get_invalid_field_error_when_request_field_has_invalid_value_or_format
    Given path 'price'
    And request { brandId: '#(BRAND_ID_TEST)', productId: '#(PRODUCT_ID_TEST)', priceDate: '#(INVALID_DATETIME)' }
    When method post
    Then status 400
    And assert response.errorCode == '#(INVALID_FIELD_VALUE)'

  Scenario Outline: Should get the price that applies at the requested date and time
    Given path 'price'
    And request { brandId: '#(BRAND_ID_TEST)', productId: '#(PRODUCT_ID_TEST)', priceDate: '<dateTime>' }
    When method post
    Then status 200
    And assert response.price == <expectedResult>

    Examples:
      | dateTime           | expectedResult |
      | 2020-06-14 10:00:00| 35.50          |
      | 2020-06-14 16:00:00| 25.45          |
      | 2020-06-14 21:00:00| 35.50          |
      | 2020-06-15 10:00:00| 30.50          |
      | 2020-06-16 21:00:00| 38.95          |