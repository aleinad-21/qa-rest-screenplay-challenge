package com.challenge.models.request.user;

import lombok.Builder;
import lombok.Data;

/** /* Request model used to update user in the GoREST API. */
@Data
@Builder
public class UpdateUserRequest {
  /** User name. */
  private String name;

  /** User email. */
  private String email;

  /** User gender. */
  private String gender;

  /** User status. */
  private String status;
}
