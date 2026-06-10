package com.challenge.interactions.api;

import com.challenge.utils.config.EnvironmentConfig;
import lombok.RequiredArgsConstructor;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.rest.interactions.Post;

/** Interaction responsible for executing POST requests. */
@RequiredArgsConstructor
public class PostRequest implements Interaction {

  /** API endpoint path. */
  private final String endpoint;

  /** Request body payload. */
  private final Object body;

  @Override
  public <User extends Actor> void performAs(User actor) {
    actor.attemptsTo(
        Post.to(endpoint)
            .with(
                request ->
                    request
                        .header("Authorization", "Bearer " + EnvironmentConfig.getToken())
                        .contentType("application/json")
                        .body(body)));
  }
}
