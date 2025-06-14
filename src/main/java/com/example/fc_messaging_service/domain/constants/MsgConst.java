package com.example.fc_messaging_service.domain.constants;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MsgConst {
  public static final String BAD_REQUEST_MSG =
      "The request could not be processed due to invalid or incomplete data.";
  public static final String SERVER_ERROR_MSG =
      "An unexpected server error occurred. Please try again later.";
  public static final String MESSAGE_SEND_SUCCESSFULLY_MSG = "The message was send successfully.";
}
