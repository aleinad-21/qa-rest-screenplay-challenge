package com.challenge.tests;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.*;

import com.challenge.factories.UserDataFactory;
import com.challenge.models.request.user.CreateUserRequest;
import com.challenge.models.request.user.UpdateUserRequest;
import com.challenge.questions.ApiResult;
import com.challenge.tasks.CreateUser;
import com.challenge.tasks.UpdateUser;
import com.challenge.utils.constants.SessionVariables;
import com.challenge.tests.base.BaseConfigTest;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

/** Test suite for Update User endpoint. */
@ExtendWith(SerenityJUnit5Extension.class)
public class UpdateUserTest extends BaseConfigTest {

  /** Creates a user required by update scenarios. */
  private void createUser() {

    CreateUserRequest request = UserDataFactory.createRandomUser();

    actor.attemptsTo(new CreateUser(request));
  }

  /** Validates that an existing user can be updated successfully. */
  @Test
  void shouldUpdateUserSuccessfully() {

    createUser();

    UpdateUserRequest request = UserDataFactory.createUpdatedUser();

    actor.attemptsTo(new UpdateUser(request));

    actor.should(
        seeThat(ApiResult.statusCode(), equalTo(200)),
        seeThat(ApiResult.intField("id"), notNullValue()),
        seeThat(ApiResult.field("name"), equalTo(request.getName())),
        seeThat(ApiResult.field("email"), equalTo(request.getEmail())),
        seeThat(ApiResult.field("gender"), equalTo(request.getGender())),
        seeThat(ApiResult.field("status"), equalTo(request.getStatus())),
        seeThat(ApiResult.contentType(), containsString("application/json")));
  }

  /** Validates that a user cannot be updated without email. */
  @Test
  void shouldNotUpdateUserWithoutEmail() {

    createUser();

    UpdateUserRequest request = UserDataFactory.createUpdatedUser();

    request.setEmail(null);

    actor.attemptsTo(new UpdateUser(request));

    actor.should(
        seeThat(ApiResult.statusCode(), equalTo(422)),
        seeThat(ApiResult.errorField(), equalTo("email")),
        seeThat(ApiResult.errorMessage(), containsString("can't be blank")));
  }

  /** Validates that a user cannot be updated without name. */
  @Test
  void shouldNotUpdateUserWithoutName() {

    createUser();

    UpdateUserRequest request = UserDataFactory.createUpdatedUser();

    request.setName(null);

    actor.attemptsTo(new UpdateUser(request));

    actor.should(
        seeThat(ApiResult.statusCode(), equalTo(422)),
        seeThat(ApiResult.errorField(), equalTo("name")),
        seeThat(ApiResult.errorMessage(), containsString("can't be blank")));
  }

  /** Validates that a user cannot be updated using an invalid gender. */
  @Test
  void shouldNotUpdateUserWithInvalidGender() {

    createUser();

    UpdateUserRequest request = UserDataFactory.createUpdatedUser();

    request.setGender("other");

    actor.attemptsTo(new UpdateUser(request));

    actor.should(
        seeThat(ApiResult.statusCode(), equalTo(422)),
        seeThat(ApiResult.errorField(), equalTo("gender")));
  }

  /** Validates that a user cannot be updated using an invalid status. */
  @Test
  void shouldNotUpdateUserWithInvalidStatus() {

    createUser();

    UpdateUserRequest request = UserDataFactory.createUpdatedUser();

    request.setStatus("blocked");

    actor.attemptsTo(new UpdateUser(request));

    actor.should(
        seeThat(ApiResult.statusCode(), equalTo(422)),
        seeThat(ApiResult.errorField(), equalTo("status")));
  }

  /** Validates that an update fails when the user does not exist. */
  @Test
  void shouldNotUpdateNonExistingUser() {

    actor.remember(SessionVariables.USER_ID.getValue(), 232);

    UpdateUserRequest request = UserDataFactory.createUpdatedUser();

    actor.attemptsTo(new UpdateUser(request));

    actor.should(seeThat(ApiResult.statusCode(), equalTo(404)));
  }
}
