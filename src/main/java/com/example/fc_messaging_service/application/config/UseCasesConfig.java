package com.example.fc_messaging_service.application.config;

import com.example.fc_messaging_service.domain.api.MessagingServicePort;
import com.example.fc_messaging_service.domain.spi.OrderPinPersistencePort;
import com.example.fc_messaging_service.domain.spi.SmsServicePort;
import com.example.fc_messaging_service.domain.spi.UserServicePort;
import com.example.fc_messaging_service.domain.usecase.MessagingUseCase;
import com.example.fc_messaging_service.infrastructure.adapters.persistence.adapter.OrderPinPersistenceAdapter;
import com.example.fc_messaging_service.infrastructure.adapters.persistence.mapper.OrderPinEntityMapper;
import com.example.fc_messaging_service.infrastructure.adapters.persistence.repository.OrderPinRepository;
import com.example.fc_messaging_service.infrastructure.adapters.twiliio_service.adapter.SmsServiceAdapter;
import com.example.fc_messaging_service.infrastructure.adapters.user_service.UserServiceAdapter;
import com.example.fc_messaging_service.infrastructure.adapters.user_service.feign.UserFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class UseCasesConfig {

  private final UserFeignClient userFeignClient;
  private final OrderPinEntityMapper orderPinEntityMapper;
  private final OrderPinRepository orderPinRepository;

  @Bean
  public SmsServicePort smsServicePort() {
    return new SmsServiceAdapter();
  }

  @Bean
  public UserServicePort userServicePort() {
    return new UserServiceAdapter(userFeignClient);
  }

  @Bean
  public OrderPinPersistencePort orderPinPersistencePort() {
    return new OrderPinPersistenceAdapter(orderPinEntityMapper, orderPinRepository);
  }

  @Bean
  public MessagingServicePort messagingServicePort(
      SmsServicePort smsServicePort,
      UserServicePort userServicePort,
      OrderPinPersistencePort orderPinPersistencePort) {
    return new MessagingUseCase(smsServicePort, userServicePort, orderPinPersistencePort);
  }
}
