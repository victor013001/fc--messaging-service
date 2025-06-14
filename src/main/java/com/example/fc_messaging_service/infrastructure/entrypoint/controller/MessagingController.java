package com.example.fc_messaging_service.infrastructure.entrypoint.controller;

import static com.example.fc_messaging_service.domain.constants.HttpStatusConst.BAD_REQUEST;
import static com.example.fc_messaging_service.domain.constants.HttpStatusConst.OK;
import static com.example.fc_messaging_service.domain.constants.HttpStatusConst.OK_INT;
import static com.example.fc_messaging_service.domain.constants.HttpStatusConst.SERVER_ERROR;
import static com.example.fc_messaging_service.domain.constants.MsgConst.BAD_REQUEST_MSG;
import static com.example.fc_messaging_service.domain.constants.MsgConst.MESSAGE_SEND_SUCCESSFULLY_MSG;
import static com.example.fc_messaging_service.domain.constants.MsgConst.SERVER_ERROR_MSG;
import static com.example.fc_messaging_service.domain.constants.RouterConst.MESSAGING_BASE_PATH;
import static com.example.fc_messaging_service.domain.constants.RouterConst.ORDER_BASE_PATH;
import static com.example.fc_messaging_service.domain.constants.RouterConst.PIN_BASE_PATH;
import static com.example.fc_messaging_service.domain.constants.RouterConst.USER_BASE_PATH;
import static com.example.fc_messaging_service.domain.constants.SwaggerConst.SEND_MESSAGE_OPERATION;
import static com.example.fc_messaging_service.domain.constants.SwaggerConst.VALID_PIN_OPERATION;
import static com.example.fc_messaging_service.domain.enums.ServerResponses.MESSAGE_SEND_SUCCESSFULLY;

import com.example.fc_messaging_service.application.service.MessagingApplicationService;
import com.example.fc_messaging_service.domain.exceptions.StandardError;
import com.example.fc_messaging_service.infrastructure.entrypoint.dto.DefaultServerResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(MESSAGING_BASE_PATH)
public class MessagingController {

  private final MessagingApplicationService messagingApplicationService;

  @Operation(summary = SEND_MESSAGE_OPERATION)
  @ApiResponses(
      value = {
        @ApiResponse(responseCode = OK, description = MESSAGE_SEND_SUCCESSFULLY_MSG),
        @ApiResponse(responseCode = BAD_REQUEST, description = BAD_REQUEST_MSG),
        @ApiResponse(responseCode = SERVER_ERROR, description = SERVER_ERROR_MSG),
      })
  @PostMapping(ORDER_BASE_PATH + "/{order_id}" + USER_BASE_PATH + "/{user_id}")
  @PreAuthorize("hasAuthority('employee')")
  public ResponseEntity<DefaultServerResponse<String, StandardError>> sendMessage(
      @PathVariable(name = "order_id") Long orderId, @PathVariable(name = "user_id") Long userId) {
    messagingApplicationService.sendMessage(orderId, userId);
    return ResponseEntity.status(MESSAGE_SEND_SUCCESSFULLY.getHttpStatus())
        .body(new DefaultServerResponse<>(MESSAGE_SEND_SUCCESSFULLY.getMessage(), null));
  }

  @Operation(summary = VALID_PIN_OPERATION)
  @ApiResponses(
      value = {
        @ApiResponse(responseCode = OK, description = ""),
        @ApiResponse(responseCode = SERVER_ERROR, description = SERVER_ERROR_MSG),
      })
  @GetMapping(ORDER_BASE_PATH + "/{order_id}" + PIN_BASE_PATH + "/{pin}")
  @PreAuthorize("hasAuthority('employee')")
  public ResponseEntity<DefaultServerResponse<Boolean, StandardError>> validPin(
      @PathVariable(name = "order_id") Long orderId, @PathVariable(name = "pin") Integer pin) {
    return ResponseEntity.status(OK_INT)
        .body(
            new DefaultServerResponse<>(messagingApplicationService.validPin(orderId, pin), null));
  }
}
