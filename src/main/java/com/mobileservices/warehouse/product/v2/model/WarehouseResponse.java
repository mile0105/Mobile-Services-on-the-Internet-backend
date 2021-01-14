package com.mobileservices.warehouse.product.v2.model;

import com.mobileservices.warehouse.product.v1.model.ProductResponse;
import lombok.Builder;
import lombok.Getter;

import java.util.Set;

@Getter
@Builder
public class WarehouseResponse {
  private Long id;
  private String name;
  private Set<ProductResponse> products;
}
