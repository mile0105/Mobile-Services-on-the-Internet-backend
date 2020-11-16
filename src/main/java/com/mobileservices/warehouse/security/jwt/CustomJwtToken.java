package com.mobileservices.warehouse.security.jwt;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomJwtToken {

  private String accessToken;
  private String refreshToken;

}
