package com.challenge.tasks;

import com.challenge.abilities.AuthenticateWithApi;
import com.challenge.models.request.user.UpdateUserRequest;
import com.challenge.utils.constants.ApiResources;
import com.challenge.utils.constants.SessionVariables;
import lombok.RequiredArgsConstructor;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Put;

/** Task responsible for updating a user in GoRest API. */
@RequiredArgsConstructor
public class UpdateUser implements Task {

  /** Request payload used to update the user. */
  private final UpdateUserRequest payload;

  @Override
  public <T extends Actor> void performAs(T actor) {

    String token = actor.abilityTo(AuthenticateWithApi.class).getToken();

    Integer userId = actor.recall(SessionVariables.USER_ID.getValue());

    // Save payload in actor memory
    actor.remember(SessionVariables.UPDATE_USER_REQUEST.getValue(), payload);

    actor.attemptsTo(
        Put.to(ApiResources.USER_BY_ID.getPath().replace("{id}", String.valueOf(userId)))
            .with(
                request ->
                    request
                        .header("Authorization", "Bearer " + token)
                        .contentType("application/json")
                        .body(payload)));
  }
}
