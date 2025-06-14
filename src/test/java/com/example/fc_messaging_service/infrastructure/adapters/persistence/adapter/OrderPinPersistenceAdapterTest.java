package com.example.fc_messaging_service.infrastructure.adapters.persistence.adapter;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.fc_messaging_service.domain.model.OrderPin;
import com.example.fc_messaging_service.infrastructure.adapters.persistence.entity.OrderPinEntity;
import com.example.fc_messaging_service.infrastructure.adapters.persistence.mapper.OrderPinEntityMapper;
import com.example.fc_messaging_service.infrastructure.adapters.persistence.mapper.OrderPinEntityMapperImpl;
import com.example.fc_messaging_service.infrastructure.adapters.persistence.repository.OrderPinRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class OrderPinPersistenceAdapterTest {

  @InjectMocks private OrderPinPersistenceAdapter orderPinPersistenceAdapter;

  @Mock private OrderPinRepository orderPinRepository;

  @Spy private OrderPinEntityMapper orderPinEntityMapper = new OrderPinEntityMapperImpl();

  @Test
  void shouldSave() {
    var orderPin = new OrderPin(1L, 1234);
    orderPinPersistenceAdapter.save(orderPin);
    verify(orderPinRepository).save(any(OrderPinEntity.class));
  }

  @Test
  void validPin() {
    var orderId = 1L;
    var pin = 1234;
    when(orderPinRepository.existsByOrderIdAndPin(orderId, pin)).thenReturn(true);
    var exists = orderPinPersistenceAdapter.existsByOrderIdAndPin(orderId, pin);
    assertTrue(exists);
    verify(orderPinRepository).existsByOrderIdAndPin(orderId, pin);
  }
}
