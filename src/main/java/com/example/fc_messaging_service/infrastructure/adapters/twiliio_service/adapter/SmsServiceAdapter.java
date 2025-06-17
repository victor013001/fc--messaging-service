package com.example.fc_messaging_service.infrastructure.adapters.twiliio_service.adapter;

import com.example.fc_messaging_service.domain.spi.SmsServicePort;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SmsServiceAdapter implements SmsServicePort {

  @Value("${twilio.from-number}")
  private String fromNumber;

  @Override
  public void sendSms(String to, String message) {
    Message.creator(new PhoneNumber("+18777804236"), new PhoneNumber(fromNumber), message).create();
  }
}
