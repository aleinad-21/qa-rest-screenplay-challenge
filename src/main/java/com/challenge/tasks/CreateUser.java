package com.challenge.tasks;

import com.challenge.interactions.api.PostRequest;
import com.challenge.models.request.user.CreateUserRequest;
import com.challenge.utils.constants.ApiResources;
import lombok.RequiredArgsConstructor;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

/** Task responsible for creating a new user through the GoREST. */
@RequiredArgsConstructor
public class CreateUser implements Task {

  /** Request payload used to create the user. */
  private final CreateUserRequest request;

  /**
   * Executes the user creation process.
   *
   * @param actor the actor performing the task
   */
  @Override
  public <T extends Actor> void performAs(T actor) {

    actor.attemptsTo(new PostRequest(ApiResources.USERS.getPath(), request));
  }
}
