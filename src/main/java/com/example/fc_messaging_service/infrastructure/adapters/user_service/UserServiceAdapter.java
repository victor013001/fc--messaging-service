package com.example.fc_messaging_service.infrastructure.adapters.user_service;

import com.example.fc_messaging_service.domain.exceptions.standard_exception.BadRequest;
import com.example.fc_messaging_service.domain.spi.UserServicePort;
import com.example.fc_messaging_service.infrastructure.adapters.user_service.feign.UserFeignClient;
import com.example.fc_messaging_service.infrastructure.entrypoint.dto.DefaultServerResponse;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserServiceAdapter implements UserServicePort {
  private static final String LOG_PREFIX = "[USER_SERVICE_ADAPTER] >>> ";

  private final UserFeignClient userFeignClient;

  @Override
  public String getUserPhone(Long userId) {
    log.info("{} Getting user: {} phone", LOG_PREFIX, userId);
    return Optional.of(userFeignClient.getUserPhone(userId))
        .map(DefaultServerResponse::data)
        .orElseThrow(BadRequest::new);
  }
}
