package com.example.fc_messaging_service.domain.spi;

import com.example.fc_messaging_service.domain.model.OrderPin;

public interface OrderPinPersistencePort {
  void save(OrderPin orderPin);

  Boolean existsByOrderIdAndPin(Long orderId, Integer pin);
}
