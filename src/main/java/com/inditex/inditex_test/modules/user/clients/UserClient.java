package com.inditex.inditex_test.modules.user.clients;

import com.inditex.inditex_test.modules.user.dtos.UserDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Log4j2
public class UserClient {

  /**
   * Simulates a call to an external service to get user information.
   *
   * @param userId The ID of the user.
   * @return A Mono containing the UserDto object with user information.
   */
  public Mono<UserDto> getUserInformation(long userId) {
    return Mono.fromCallable(
            () -> {
              UserDto user = new UserDto();
              user.id = userId;
              user.nickname = "test_user";
              user.countryId = "Spain";
              return user;
            });
  }
}
