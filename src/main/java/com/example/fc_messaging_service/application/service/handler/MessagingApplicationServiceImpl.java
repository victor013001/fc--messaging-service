package com.example.fc_messaging_service.application.service.handler;

import com.example.fc_messaging_service.application.service.MessagingApplicationService;
import com.example.fc_messaging_service.domain.api.MessagingServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessagingApplicationServiceImpl implements MessagingApplicationService {

  private final MessagingServicePort messagingServicePort;

  @Override
  public void sendMessage(Long orderId, Long userId) {
    messagingServicePort.sendOrderPin(orderId, userId);
  }

  @Override
  public Boolean validPin(Long orderId, Integer pin) {
    return messagingServicePort.isValidPin(orderId, pin);
  }
}
