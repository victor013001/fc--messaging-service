package com.example.fc_messaging_service.domain.api;

public interface MessagingServicePort {
  void sendOrderPin(Long orderId, Long userId);

  Boolean isValidPin(Long orderId, Integer pin);
}
