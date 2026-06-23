package com.challenge.abilities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.serenitybdd.screenplay.Ability;

/** Ability responsible for providing an API authentication token. */
@Getter
@RequiredArgsConstructor
public class AuthenticateWithApi implements Ability {

  private final String token;

  /**
   * Create a new ability instance.
   *
   * @param token authentication token
   * @return ability instance
   */
  public static AuthenticateWithApi using(String token) {
    return new AuthenticateWithApi(token);
  }
}
