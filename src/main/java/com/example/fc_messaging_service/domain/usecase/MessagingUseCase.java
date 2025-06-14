package com.example.fc_messaging_service.domain.usecase;

import com.example.fc_messaging_service.domain.api.MessagingServicePort;
import com.example.fc_messaging_service.domain.model.OrderPin;
import com.example.fc_messaging_service.domain.spi.OrderPinPersistencePort;
import com.example.fc_messaging_service.domain.spi.SmsServicePort;
import com.example.fc_messaging_service.domain.spi.UserServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessagingUseCase implements MessagingServicePort {

  private final SmsServicePort smsServicePort;
  private final UserServicePort userServicePort;
  private final OrderPinPersistencePort orderPinPersistencePort;

  @Override
  public void sendOrderPin(Long orderId, Long userId) {
    String userPhone = userServicePort.getUserPhone(userId);
    Integer pin = createPin();
    orderPinPersistencePort.save(new OrderPin(orderId, pin));
    smsServicePort.sendSms(userPhone, createPinMessage(orderId, pin));
  }

  @Override
  public Boolean isValidPin(Long orderId, Integer pin) {
    return orderPinPersistencePort.existsByOrderIdAndPin(orderId, pin);
  }

  private String createPinMessage(Long orderId, Integer pin) {
    return String.format(
        "Your order with ID %d is ready. You will receive it shortly. Please provide the following 4-digit PIN to receive it: %04d",
        orderId, pin);
  }

  private Integer createPin() {
    return (int) (Math.random() * 9000) + 1000;
  }
}
