package com.example.fc_messaging_service.domain.spi;

public interface SmsServicePort {
  void sendSms(String to, String message);
}
