package com.example.fc_messaging_service.application.dto;

public record DefaultServerResponse<T, E>(T data, E error) {}
