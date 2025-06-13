package com.example.fc_messaging_service.infrastructure.adapters.user_service.feign;

import com.example.fc_messaging_service.domain.exceptions.StandardError;
import com.example.fc_messaging_service.infrastructure.entrypoint.dto.DefaultServerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "user-service", url = "http://localhost:8080")
public interface UserFeignClient {

  @GetMapping("/api/v1/user/{user_id}/phone")
  DefaultServerResponse<String, StandardError> getUserPhone(Long userId);
}
