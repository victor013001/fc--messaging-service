package com.example.non_reactive_archetype.domain.constants;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MsgConst {
  public static final String BAD_REQUEST_MSG =
      "The request could not be processed due to invalid or incomplete data.";
  public static final String SERVER_ERROR_MSG =
      "An unexpected server error occurred. Please try again later.";
}
