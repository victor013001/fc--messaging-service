package com.example.fc_messaging_service;

import com.example.fc_messaging_service.config.TestcontainersConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest
@Import(TestcontainersConfig.class)
class FCMessagingServiceApplicationTests {

  @Test
  void contextLoads() {
    // This method is intentionally left empty to verify that the Spring application context loads
  }
}
