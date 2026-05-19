package com.challenge.utils.config;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.serenitybdd.model.environment.ConfiguredEnvironment;
import net.thucydides.model.util.EnvironmentVariables;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class EnvironmentConfig {

    private static final EnvironmentVariables ENVIRONMENT_VARIABLES =
            ConfiguredEnvironment.getEnvironmentVariables();

    public static String getBaseUrl() {
        return ENVIRONMENT_VARIABLES.getProperty("base.url");
    }

    public static String getToken() {
        return ENVIRONMENT_VARIABLES.getProperty("secret.key");
    }
}