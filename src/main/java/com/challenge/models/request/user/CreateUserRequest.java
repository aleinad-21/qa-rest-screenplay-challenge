package com.challenge.models.request.user;

import lombok.Builder;
import lombok.Data;

/** Request model used to create a new user in the GoREST API. */
@Data
@Builder
public class CreateUserRequest {

  /** User full name. */
  private String name;

  /** User email address. */
  private String email;

  /** User gender. */
  private String gender;

  /** User status. */
  private String status;
}
