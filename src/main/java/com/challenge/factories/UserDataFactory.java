package com.challenge.factories;

import com.challenge.models.request.user.CreateUserRequest;
import com.challenge.models.request.user.UpdateUserRequest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.datafaker.Faker;

/** Factory class responsible for generating dynamic user test data. */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class UserDataFactory {

  /** Faker instance used to generate random data. */
  private static final Faker FAKER = new Faker();

  /**
   * Random user request object.
   *
   * @return a CreateUserRequest with random data
   */
  public static CreateUserRequest createRandomUser() {
    return CreateUserRequest.builder()
        .name(FAKER.name().fullName())
        .email(FAKER.internet().emailAddress())
        .gender("female")
        .status("active")
        .build();
  }

  /**
   * Set data user request object.
   *
   * @return a CreateUserRequest with random data
   */
  public static UpdateUserRequest createUpdatedUser() {
    return UpdateUserRequest.builder()
        .name("Tyber Updated")
        .email("tyber" + FAKER.number().digits(4) + "@mail.com")
        .gender("female")
        .status("active")
        .build();
  }
}
