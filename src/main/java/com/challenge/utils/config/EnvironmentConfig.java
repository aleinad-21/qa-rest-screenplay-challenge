package com.challenge.utils.config;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.serenitybdd.model.environment.ConfiguredEnvironment;
import net.thucydides.model.util.EnvironmentVariables;

/**
 * Utility class responsible for retrieving environment configuration values used across the
 * automation framework.
 *
 * <p>This class centralizes access to properties defined in the serenity.conf configuration file.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class EnvironmentConfig {

  /** Serenity environment variables instance used to access configuration properties. */
  private static final EnvironmentVariables ENVIRONMENT_VARIABLES =
      ConfiguredEnvironment.getEnvironmentVariables();

  /**
   * Returns the base URL configured for the REST API.
   *
   * @return the API base URL
   */
  public static String getBaseUrl() {
    return ENVIRONMENT_VARIABLES.getProperty("base.url");
  }

  /**
   * Returns the authentication token configured for the API.
   *
   * @return the API authentication token
   */
  public static String getToken() {
    return ENVIRONMENT_VARIABLES.getProperty("secret.key");
  }
}
