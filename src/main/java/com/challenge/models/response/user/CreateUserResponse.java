package com.challenge.models.response.user;

import lombok.Data;

/** Request model used to create a new user in the GoREST API. */
@Data
public class CreateUserResponse {

  /** User id create. */
  private Integer id;

  /** User full name. */
  private String name;

  /** User email address. */
  private String email;

  /** User gender. */
  private String gender;

  /** User status. */
  private String status;
}
