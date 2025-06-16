package com.example.fc_messaging_service.infrastructure.adapters.user_service.feign;

import com.example.fc_messaging_service.application.dto.DefaultServerResponse;
import com.example.fc_messaging_service.domain.exceptions.StandardError;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service", url = "http://localhost:8080")
public interface UserFeignClient {

  @GetMapping("/api/v1/user/{user_id}/phone")
  DefaultServerResponse<String, StandardError> getUserPhone(
      @PathVariable(name = "user_id") Long userId);
}
