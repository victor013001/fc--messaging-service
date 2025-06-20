package com.example.fc_messaging_service.domain.constants;

import lombok.experimental.UtilityClass;

@UtilityClass
public class HttpStatusConst {
  public static final String BAD_REQUEST = "400";
  public static final String SERVER_ERROR = "500";
  public static final String OK = "200";

  public static final int BAD_REQUEST_INT = 400;
  public static final int SERVER_ERROR_INT = 500;
  public static final int OK_INT = 200;
}
