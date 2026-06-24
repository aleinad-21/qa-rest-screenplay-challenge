package com.challenge.utils.constants;

import lombok.Getter;

/** Enum that contains the available API resource endpoints. */
@Getter
public enum ApiResources {

  /** Endpoint for user-related operations. */
  USERS("/users"),

  /** Endpoint for user-related update. */
  USER_BY_ID("/users/{id}");

  /** Resource path associated with the endpoint. */
  private final String path;

  /**
   * Creates a new API resource with its endpoint path.
   *
   * @param path the endpoint path value
   */
  ApiResources(String path) {
    this.path = path;
  }
}
