package com.mobileservices.warehouse.product.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ProductResponseV1 {
  private Long id;
  private String manufacturerName;
  private String modelName;
  private Double price;
  private Integer quantity;
  private LocalDateTime lastEditTimeStamp;
}
