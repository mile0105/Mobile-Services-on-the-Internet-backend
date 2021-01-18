package com.mobileservices.warehouse.product.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ProductApiV2 {
  private String manufacturerName;
  private String modelName;
  private Double price;
  private Double priceInNaira;
  private LocalDateTime lastEditTimeStamp;
}
