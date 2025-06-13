package com.example.fc_messaging_service.application.service.handler;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import com.example.fc_messaging_service.domain.api.MessagingServicePort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MessagingApplicationServiceImplTest {

  @Mock
  private MessagingServicePort messagingServicePort;

  @InjectMocks
  private MessagingApplicationServiceImpl messagingApplicationService;

  @Test
  void sendMessage_shouldDelegateToMessagingServicePort() {
    Long orderId = 1L;
    Long userId = 42L;

    messagingApplicationService.sendMessage(orderId, userId);

    verify(messagingServicePort).sendOrderPin(orderId, userId);
    verifyNoMoreInteractions(messagingServicePort);
  }
}
