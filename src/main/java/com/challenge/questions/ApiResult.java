package com.challenge.questions;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Question;

/** Generic questions used to validate REST API responses. */
public final class ApiResult {

  private ApiResult() {}

  /**
   * Returns the response status code.
   *
   * @return status code question
   */
  public static Question<Integer> statusCode() {

    return Question.about("Response status code")
        .answeredBy(actor -> SerenityRest.lastResponse().statusCode());
  }

  /**
   * Returns the response content type.
   *
   * @return content type question
   */
  public static Question<String> contentType() {

    return Question.about("Response content type")
        .answeredBy(actor -> SerenityRest.lastResponse().getContentType());
  }

  /**
   * Returns a string field from the response body.
   *
   * @param field JSON field name
   * @return field value question
   */
  public static Question<String> field(String field) {

    return Question.about("Response field: " + field)
        .answeredBy(actor -> SerenityRest.lastResponse().jsonPath().getString(field));
  }

  /**
   * Returns an integer field from the response body.
   *
   * @param field JSON field name
   * @return field value question
   */
  public static Question<Integer> intField(String field) {

    return Question.about("Response integer field: " + field)
        .answeredBy(actor -> SerenityRest.lastResponse().jsonPath().getInt(field));
  }

  /**
   * Returns the field associated with the first validation error.
   *
   * @return error field question
   */
  public static Question<String> errorField() {

    return Question.about("Validation error field")
        .answeredBy(actor -> SerenityRest.lastResponse().jsonPath().getString("[0].field"));
  }

  /**
   * Returns the first validation error message.
   *
   * @return error message question
   */
  public static Question<String> errorMessage() {

    return Question.about("Validation error message")
        .answeredBy(actor -> SerenityRest.lastResponse().jsonPath().getString("[0].message"));
  }
}
