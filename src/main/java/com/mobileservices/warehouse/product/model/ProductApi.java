package com.mobileservices.warehouse.product.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ProductApi {
  private String manufacturerName;
  private String modelName;
  private double price;
  private LocalDateTime lastEditTimeStamp;
}
