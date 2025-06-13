package com.example.fc_messaging_service.infrastructure.adapters.user_service;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.fc_messaging_service.infrastructure.adapters.user_service.feign.UserFeignClient;
import com.example.fc_messaging_service.infrastructure.entrypoint.dto.DefaultServerResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserServiceAdapterTest {

  @InjectMocks private UserServiceAdapter userServiceAdapter;

  @Mock private UserFeignClient userFeignClient;

  @Test
  void shouldReturnUserPhone() {
    when(userFeignClient.getUserPhone(1L))
        .thenReturn(new DefaultServerResponse<>("+1234567890123", null));

    userServiceAdapter.getUserPhone(1L);

    verify(userFeignClient).getUserPhone(1L);
  }
}
