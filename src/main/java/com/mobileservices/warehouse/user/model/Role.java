package com.mobileservices.warehouse.user.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
  WAREHOUSE_MANAGER("MANAGER"),
  WAREHOUSE_EMPLOYEE("EMPLOYEE");

  private final String name;
}
