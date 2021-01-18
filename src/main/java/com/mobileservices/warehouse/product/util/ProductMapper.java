package com.mobileservices.warehouse.product.util;

import com.mobileservices.warehouse.product.model.Product;
import com.mobileservices.warehouse.product.model.ProductApiV1;
import com.mobileservices.warehouse.product.model.ProductApiV2;
import com.mobileservices.warehouse.product.model.ProductResponseV1;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

  private final double dollarToNaira = 381.20;

  public Product mapToDbModel(ProductApiV2 productApiV2, Long id, Integer quantity) {

    double priceInDollars = productApiV2.getPrice() == null ? productApiV2.getPriceInNaira()/dollarToNaira : productApiV2.getPrice();
    double priceInNaira = productApiV2.getPriceInNaira() == null? productApiV2.getPrice() * dollarToNaira: productApiV2.getPriceInNaira();

    return Product.builder()
      .manufacturerName(productApiV2.getManufacturerName())
      .modelName(productApiV2.getModelName())
      .price(priceInDollars)
      .priceInNaira(priceInNaira)
      .quantity(quantity)
      .id(id)
      .build();
  }

  public Product mapToDbModel(ProductApiV1 productApiV1, Long id, Integer quantity) {
    return Product.builder()
      .manufacturerName(productApiV1.getManufacturerName())
      .modelName(productApiV1.getModelName())
      .price(productApiV1.getPrice())
      .priceInNaira(productApiV1.getPrice() * dollarToNaira)
      .quantity(quantity)
      .id(id)
      .build();
  }

  public ProductResponseV1 mapToProductResponseV1(Product product) {
    return ProductResponseV1.builder()
      .manufacturerName(product.getManufacturerName())
      .modelName(product.getModelName())
      .price(product.getPrice())
      .quantity(product.getQuantity())
      .id(product.getId())
      .build();
  }

  public Product copyProduct(Product other) {
    return Product.builder()
      .id(other.getId())
      .manufacturerName(other.getManufacturerName())
      .modelName(other.getModelName())
      .price(other.getPrice())
      .priceInNaira(other.getPriceInNaira())
      .quantity(other.getQuantity())
      .build();
  }

}
