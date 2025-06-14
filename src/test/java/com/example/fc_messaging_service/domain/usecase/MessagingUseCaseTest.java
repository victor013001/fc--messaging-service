package com.example.fc_messaging_service.domain.usecase;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.fc_messaging_service.domain.model.OrderPin;
import com.example.fc_messaging_service.domain.spi.OrderPinPersistencePort;
import com.example.fc_messaging_service.domain.spi.SmsServicePort;
import com.example.fc_messaging_service.domain.spi.UserServicePort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MessagingUseCaseTest {

  @InjectMocks private MessagingUseCase messagingUseCase;

  @Mock private SmsServicePort smsServicePort;

  @Mock private UserServicePort userServicePort;

  @Mock private OrderPinPersistencePort orderPinPersistencePort;

  @Test
  void shouldSendOrderPin() {
    Long orderId = 1L;
    Long userId = 2L;
    String userPhone = "+123456789";

    when(userServicePort.getUserPhone(userId)).thenReturn(userPhone);

    messagingUseCase.sendOrderPin(orderId, userId);

    verify(userServicePort).getUserPhone(userId);
    verify(orderPinPersistencePort).save(any(OrderPin.class));
    verify(smsServicePort)
        .sendSms(
            eq(userPhone),
            argThat(
                message ->
                    message.contains("Your order with ID 1 is ready.")
                        && message.matches(".*\\d{4}$")));
  }
}
