package com.example.fc_messaging_service.infrastructure.adapters.persistence.repository;

import com.example.fc_messaging_service.infrastructure.adapters.persistence.entity.OrderPinEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderPinRepository extends JpaRepository<OrderPinEntity, Long> {
  Boolean existsByOrderIdAndPin(Long orderId, Integer pin);
}
