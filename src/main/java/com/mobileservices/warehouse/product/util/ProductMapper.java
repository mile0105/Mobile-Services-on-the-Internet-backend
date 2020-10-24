package com.mobileservices.warehouse.product.util;

import com.mobileservices.warehouse.product.model.Product;
import com.mobileservices.warehouse.product.model.ProductApi;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

  public Product mapToDbModel(ProductApi productApi, Long id, Integer quantity) {
    return Product.builder()
      .manufacturerName(productApi.getManufacturerName())
      .modelName(productApi.getModelName())
      .price(productApi.getPrice())
      .quantity(quantity)
      .id(id)
      .build();
  }

  public Product copyProduct(Product other) {
    return Product.builder()
      .id(other.getId())
      .manufacturerName(other.getManufacturerName())
      .modelName(other.getModelName())
      .price(other.getPrice())
      .quantity(other.getQuantity())
      .build();
  }

}
