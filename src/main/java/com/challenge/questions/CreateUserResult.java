package com.challenge.questions;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Question;

/** Question responsible for retrieving information from the create user response. */
public final class CreateUserResult {

  private CreateUserResult() {}

  /**
   * Returns the response status code.
   *
   * @return status code question
   */
  public static Question<Integer> statusCode() {

    return Question.about("Response Status code")
        .answeredBy(actor -> SerenityRest.lastResponse().statusCode());
  }

  /**
   * Returns the created user identifier.
   *
   * @return user id question
   */
  public static Question<Integer> userId() {
    return Question.about("Created user id")
        .answeredBy(actor -> SerenityRest.lastResponse().jsonPath().getInt("id"));
  }

  /**
   * Returns the created user name.
   *
   * @return name question
   */
  public static Question<String> name() {
    return Question.about("Created user name")
        .answeredBy(actor -> SerenityRest.lastResponse().jsonPath().getString("name"));
  }

  /**
   * Returns the created user email.
   *
   * @return email question
   */
  public static Question<String> email() {
    return Question.about("Created user email")
        .answeredBy(actor -> SerenityRest.lastResponse().jsonPath().getString("email"));
  }

  /**
   * Returns the created user gender.
   *
   * @return gender question
   */
  public static Question<String> gender() {
    return Question.about("Created user gender")
        .answeredBy(actor -> SerenityRest.lastResponse().jsonPath().getString("gender"));
  }

  /**
   * Returns the created user status.
   *
   * @return status question
   */
  public static Question<String> userStatus() {
    return Question.about("Created user status")
        .answeredBy(actor -> SerenityRest.lastResponse().jsonPath().getString("status"));
  }

  /**
   * Returns the response content type.
   *
   * @return content type question
   */
  public static Question<String> contentType() {
    return Question.about("Created user contentType")
        .answeredBy(actor -> SerenityRest.lastResponse().getContentType());
  }

  /**
   * Returns the field associated with the first validation error.
   *
   * @return field name question
   */
  public static Question<String> errorField() {
    return Question.about("Description message error type")
        .answeredBy(actor -> SerenityRest.lastResponse().jsonPath().getString("[0].field"));
  }

  /**
   * Returns the first validation error message.
   *
   * @return error message question
   */
  public static Question<String> errorMessage() {
    return Question.about("Description message error")
        .answeredBy(actor -> SerenityRest.lastResponse().jsonPath().getString("[0].message"));
  }
}
