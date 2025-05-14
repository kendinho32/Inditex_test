package com.inditex.inditex_test.modules.user.usecases;

import com.inditex.inditex_test.modules.user.clients.UserClient;
import com.inditex.inditex_test.modules.user.dtos.InfoDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@AllArgsConstructor
public class UserService {

  private final UserClient userClient;

  public Mono<InfoDto> demoReactive(Long userId) {
    return userClient.getUserInformation(userId)
        .map(user -> InfoDto.builder().user(user).build());
  }
}
