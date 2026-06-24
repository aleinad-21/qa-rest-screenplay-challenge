package com.challenge.tests.base;

import com.challenge.abilities.AuthenticateWithApi;
import com.challenge.utils.config.EnvironmentConfig;
import io.restassured.RestAssured;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import org.junit.jupiter.api.BeforeEach;

/** Base class for all API tests. */
public abstract class BaseConfigTest {

  protected Actor actor;

  @BeforeEach
  void setUpActor() {

    RestAssured.useRelaxedHTTPSValidation();

    actor = Actor.named("API User");

    actor.can(CallAnApi.at(EnvironmentConfig.getBaseUrl()));

    actor.can(AuthenticateWithApi.using(EnvironmentConfig.getToken()));
  }
}
