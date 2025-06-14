package com.example.fc_messaging_service.infrastructure.adapters.persistence.adapter;

import com.example.fc_messaging_service.domain.model.OrderPin;
import com.example.fc_messaging_service.domain.spi.OrderPinPersistencePort;
import com.example.fc_messaging_service.infrastructure.adapters.persistence.mapper.OrderPinEntityMapper;
import com.example.fc_messaging_service.infrastructure.adapters.persistence.repository.OrderPinRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
public class OrderPinPersistenceAdapter implements OrderPinPersistencePort {
  private static final String LOG_PREFIX = "[ORDER_PIN_PERSISTENCE_ADAPTER] >>> ";

  private final OrderPinEntityMapper orderPinEntityMapper;
  private final OrderPinRepository orderPinRepository;

  @Override
  @Transactional
  public void save(OrderPin orderPin) {
    log.info("{} Saving pin for order: {}.", LOG_PREFIX, orderPin.orderId());
    orderPinRepository.save(orderPinEntityMapper.toEntity(orderPin));
  }
}
