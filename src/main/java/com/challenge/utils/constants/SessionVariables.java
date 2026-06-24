package com.challenge.utils.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/** Enumeration of keys used for session management. */
@Getter
@RequiredArgsConstructor
public enum SessionVariables {
  USER_ID("userId"),
  CREATE_USER_REQUEST("createUserRequest"),
  UPDATE_USER_REQUEST("updateUserRequest");

  private final String value;
}
