package com.example.fc_messaging_service.application.config;

import com.twilio.Twilio;
import jakarta.annotation.PostConstruct;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "twilio")
public class TwilioConfig {

  private String accountSid;
  private String authToken;
  private String fromNumber;

  @PostConstruct
  public void init() {
    Twilio.init(accountSid, authToken);
  }
}
