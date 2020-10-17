package com.mobileservices.warehouse.user.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserApi {

  private String username;
  private String password;
}
