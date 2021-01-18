package com.mobileservices.warehouse.product.util;

import com.mobileservices.warehouse.product.model.Product;
import com.mobileservices.warehouse.product.model.ProductApiV1;
import com.mobileservices.warehouse.product.model.ProductApiV2;
import com.mobileservices.warehouse.product.model.ProductResponseV1;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

  private final double dollarToEur = 0.83;

  public Product mapToDbModel(ProductApiV2 productApiV2, Long id, Integer quantity) {

    double priceInDollars = productApiV2.getPrice() == null ? productApiV2.getPriceInEur() / dollarToEur : productApiV2.getPrice();
    double priceInNaira = productApiV2.getPriceInEur() == null ? productApiV2.getPrice() * dollarToEur : productApiV2.getPriceInEur();

    return Product.builder()
      .manufacturerName(productApiV2.getManufacturerName())
      .modelName(productApiV2.getModelName())
      .price(priceInDollars)
      .priceInEur(priceInNaira)
      .quantity(quantity)
      .id(id)
      .build();
  }

  public Product mapToDbModel(ProductApiV1 productApiV1, Long id, Integer quantity) {
    return Product.builder()
      .manufacturerName(productApiV1.getManufacturerName())
      .modelName(productApiV1.getModelName())
      .price(productApiV1.getPrice())
      .priceInEur(productApiV1.getPrice() * dollarToEur)
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
      .priceInEur(other.getPriceInEur())
      .quantity(other.getQuantity())
      .build();
  }

}
