package com.challenge.tests;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.*;

import com.challenge.abilities.AuthenticateWithApi;
import com.challenge.factories.UserDataFactory;
import com.challenge.models.request.user.CreateUserRequest;
import com.challenge.questions.CreateUserResult;
import com.challenge.tasks.CreateUser;
import com.challenge.utils.config.EnvironmentConfig;
import io.restassured.RestAssured;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(SerenityJUnit5Extension.class)
public class CreateUserTest {

  private Actor actor;

  @BeforeEach
  void setUp() {

    RestAssured.useRelaxedHTTPSValidation();

    actor = Actor.named("API User");

    actor.can(CallAnApi.at(EnvironmentConfig.getBaseUrl()));
    actor.can(AuthenticateWithApi.using(EnvironmentConfig.getToken()));
  }

  @Test
  void createUserSuccessfully() {

    CreateUserRequest request = UserDataFactory.createRandomUser();

    actor.attemptsTo(new CreateUser(request));

    actor.should(
        seeThat(CreateUserResult.statusCode(), equalTo(201)),
        seeThat(CreateUserResult.userId(), notNullValue()),
        seeThat(CreateUserResult.name(), equalTo(request.getName())),
        seeThat(CreateUserResult.email(), equalTo(request.getEmail())),
        seeThat(CreateUserResult.gender(), equalTo(request.getGender())),
        seeThat(CreateUserResult.userStatus(), equalTo(request.getStatus())),
        seeThat(CreateUserResult.contentType(), containsString("application/json")));
  }

  /** Validates that a user cannot be created without an email address. */
  @Test
  void shouldNotCreateUserWithoutEmail() {

    CreateUserRequest request = UserDataFactory.createRandomUser();

    request.setEmail(null);

    actor.attemptsTo(new CreateUser(request));

    actor.should(
        seeThat(CreateUserResult.statusCode(), equalTo(422)),
        seeThat(CreateUserResult.contentType(), containsString("application/json")));
  }

  /** Validates that a user cannot be created without a name. */
  @Test
  void shouldNotCreateUserWithoutName() {

    CreateUserRequest request = UserDataFactory.createRandomUser();

    request.setName(null);

    actor.attemptsTo(new CreateUser(request));

    actor.should(
        seeThat(CreateUserResult.statusCode(), equalTo(422)),
        seeThat(CreateUserResult.contentType(), containsString("application/json")));
  }

  /** Validates that a duplicated email cannot be used to create another user. */
  @Test
  void shouldNotCreateUserWithDuplicatedEmail() {

    CreateUserRequest request = UserDataFactory.createRandomUser();

    actor.attemptsTo(new CreateUser(request));

    actor.should(seeThat(CreateUserResult.statusCode(), equalTo(201)));

    actor.attemptsTo(new CreateUser(request));

    actor.should(seeThat(CreateUserResult.statusCode(), equalTo(422)));
  }

  /** Validates that a user cannot be created with an unsupported gender value. */
  @Test
  void shouldNotCreateUserWithInvalidGender() {

    CreateUserRequest request = UserDataFactory.createRandomUser();

    request.setGender("invalid-gender");

    actor.attemptsTo(new CreateUser(request));

    actor.should(
        seeThat(CreateUserResult.statusCode(), equalTo(422)),
        seeThat(CreateUserResult.contentType(), containsString("application/json")));
  }

  /** Validates that a user cannot be created with an unsupported status value. */
  @Test
  void shouldNotCreateUserWithInvalidStatus() {

    CreateUserRequest request = UserDataFactory.createRandomUser();

    request.setStatus("blocked");

    actor.attemptsTo(new CreateUser(request));

    actor.should(
        seeThat(CreateUserResult.statusCode(), equalTo(422)),
        seeThat(CreateUserResult.contentType(), containsString("application/json")));
  }
}
