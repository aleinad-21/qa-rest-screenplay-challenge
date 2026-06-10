package com.challenge.utils.constants;

/**
 * Enum that contains the available API resource endpoints.
 *
 * <p>Each enum value represents a specific endpoint path used in REST API requests.
 */
public enum ApiResources {

  /** Endpoint for user-related operations. */
  USERS("/users");

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

  /**
   * Returns the endpoint path associated with the resource.
   *
   * @return the API endpoint path
   */
  public String getPath() {
    return path;
  }
}
