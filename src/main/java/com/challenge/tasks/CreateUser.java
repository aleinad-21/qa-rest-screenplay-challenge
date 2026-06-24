package com.challenge.tasks;

import com.challenge.abilities.AuthenticateWithApi;
import com.challenge.models.request.user.CreateUserRequest;
import com.challenge.utils.constants.ApiResources;
import com.challenge.utils.constants.SessionVariables;
import lombok.RequiredArgsConstructor;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;

/** Task responsible for creating a new user through the GoREST. */
@RequiredArgsConstructor
public class CreateUser implements Task {

  /** Request payload used to create the user. */
  private final CreateUserRequest payload;

  /**
   * Executes the user creation process.
   *
   * @param actor the actor performing the task
   */
  @Override
  public <T extends Actor> void performAs(T actor) {

    String token = actor.abilityTo(AuthenticateWithApi.class).getToken();

    actor.attemptsTo(
        Post.to(ApiResources.USERS.getPath())
            .with(
                request ->
                    request
                        .log()
                        .all()
                        .header("Authorization", "Bearer " + token)
                        .contentType("application/json")
                        .body(payload)));

    if (SerenityRest.lastResponse().statusCode() == 201) {
      actor.remember(
          SessionVariables.USER_ID.getValue(), SerenityRest.lastResponse().jsonPath().getInt("id"));
    }
  }
}
