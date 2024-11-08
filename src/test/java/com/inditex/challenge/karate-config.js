function fn() {
  var env = karate.env; // get system property 'karate.env'
  karate.log('karate.env system property was:', env);
  if (!env) {
    env = 'dev';
  }
  return {
    BRAND_ID_TEST: 1,
    PRODUCT_ID_TEST: 35455,
    DATETIME_WITH_DATA: '2020-06-14 10:00:00',
    DATETIME_WITHOUT_DATA: '2021-06-14 10:00:00',
    INVALID_DATETIME: 'invalid-datetime-string'
  };
}