package com.mobileservices.warehouse.product.v1.model;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Builder
public class ProductResponse {

  private final Long id;
  private final String manufacturerName;
  private final String modelName;
  private final Double price;
  private final Integer quantity;
}
