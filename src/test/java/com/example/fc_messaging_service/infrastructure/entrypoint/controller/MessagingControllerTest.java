package com.example.fc_messaging_service.infrastructure.entrypoint.controller;

import static com.example.fc_messaging_service.domain.constants.MsgConst.MESSAGE_SEND_SUCCESSFULLY_MSG;
import static com.example.fc_messaging_service.domain.constants.RouterConst.MESSAGING_BASE_PATH;
import static com.example.fc_messaging_service.domain.constants.RouterConst.ORDER_BASE_PATH;
import static com.example.fc_messaging_service.domain.constants.RouterConst.PIN_BASE_PATH;
import static com.example.fc_messaging_service.domain.constants.RouterConst.USER_BASE_PATH;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.fc_messaging_service.application.service.MessagingApplicationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
class MessagingControllerTest {

  @InjectMocks private MessagingController messagingController;

  @Mock private MessagingApplicationService messagingApplicationService;

  private MockMvc mockMvc;

  @BeforeEach
  void setUp() {
    mockMvc = MockMvcBuilders.standaloneSetup(messagingController).build();
  }

  @Test
  void sendMessage_Success() throws Exception {
    Long orderId = 1L;
    Long userId = 2L;

    doNothing().when(messagingApplicationService).sendMessage(orderId, userId);

    mockMvc
        .perform(
            MockMvcRequestBuilders.post(
                    MESSAGING_BASE_PATH
                        + ORDER_BASE_PATH
                        + "/"
                        + orderId
                        + USER_BASE_PATH
                        + "/"
                        + userId)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.data").value(MESSAGE_SEND_SUCCESSFULLY_MSG))
        .andExpect(jsonPath("$.error").doesNotExist());
  }

  @Test
  void validPin_Success() throws Exception {
    Long orderId = 1L;
    Integer pin = 1234;

    when(messagingApplicationService.validPin(orderId, pin)).thenReturn(true);

    mockMvc
        .perform(
            MockMvcRequestBuilders.get(
                    MESSAGING_BASE_PATH
                        + ORDER_BASE_PATH
                        + "/"
                        + orderId
                        + PIN_BASE_PATH
                        + "/"
                        + pin)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.data").value(true))
        .andExpect(jsonPath("$.error").doesNotExist());

    verify(messagingApplicationService).validPin(orderId, pin);
  }
}
