package com.challenge.models.response.user;

import lombok.Data;

/** /* Response model used to update user in the GoREST API. */
@Data
public class UpdateUserResponse {

  /** User id updated. */
  private Integer id;

  /** User name updated. */
  private String name;

  /** User email updated. */
  private String email;

  /** User gender updated. */
  private String gender;

  /** User gender updated. */
  private String status;
}
