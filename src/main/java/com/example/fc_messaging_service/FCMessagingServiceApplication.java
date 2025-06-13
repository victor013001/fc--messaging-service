package com.example.fc_messaging_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class FCMessagingServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(FCMessagingServiceApplication.class, args);
  }
}
