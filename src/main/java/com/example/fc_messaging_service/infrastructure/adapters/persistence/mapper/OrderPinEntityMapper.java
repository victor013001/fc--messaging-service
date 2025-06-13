package com.example.fc_messaging_service.infrastructure.adapters.persistence.mapper;

import com.example.fc_messaging_service.domain.model.OrderPin;
import com.example.fc_messaging_service.infrastructure.adapters.persistence.entity.OrderPinEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderPinEntityMapper {
  @Mapping(target = "id", ignore = true)
  OrderPinEntity toEntity(OrderPin orderPin);
}
