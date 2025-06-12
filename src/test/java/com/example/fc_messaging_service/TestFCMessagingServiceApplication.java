package com.example.fc_messaging_service;

import com.example.fc_messaging_service.config.TestcontainersConfig;
import org.springframework.boot.SpringApplication;

public class TestFCMessagingServiceApplication {
  public static void main(String[] args) {
    SpringApplication.from(FCMessagingServiceApplication::main)
        .with(TestcontainersConfig.class)
        .run(args);
  }
}
