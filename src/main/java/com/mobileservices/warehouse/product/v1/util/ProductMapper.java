package com.mobileservices.warehouse.product.v1.util;

import com.mobileservices.warehouse.product.v1.model.Product;
import com.mobileservices.warehouse.product.v1.model.ProductApi;
import com.mobileservices.warehouse.product.v1.model.ProductResponse;
import com.mobileservices.warehouse.product.v2.model.WarehouseProduct;
import com.mobileservices.warehouse.product.v2.repository.WarehouseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

@Component
@RequiredArgsConstructor
public class ProductMapper {

  private final WarehouseRepository warehouseRepository;

  public Product mapToDbModelForEditing(ProductApi productApi, Long id, Set<WarehouseProduct> warehouseProducts) {

    return Product.builder()
      .manufacturerName(productApi.getManufacturerName())
      .modelName(productApi.getModelName())
      .price(productApi.getPrice())
      .quantitiesPerWarehouse(warehouseProducts)
      .id(id)
      .build();
  }

  public Product mapToDbModelForAdding(ProductApi productApi) {

    Set<WarehouseProduct> warehouseProducts = new HashSet<>();
    warehouseRepository.findAll().forEach(warehouse -> {
      warehouseProducts.add(new WarehouseProduct(warehouse.getId(), null, 0));
    });

    return Product.builder()
      .manufacturerName(productApi.getManufacturerName())
      .modelName(productApi.getModelName())
      .price(productApi.getPrice())
      .quantitiesPerWarehouse(warehouseProducts)
      .id(null)
      .build();
  }

  public Product copyProduct(Product other) {
    return Product.builder()
      .id(other.getId())
      .manufacturerName(other.getManufacturerName())
      .modelName(other.getModelName())
      .price(other.getPrice())
      .quantitiesPerWarehouse(other.getQuantitiesPerWarehouse())
      .build();
  }

  public ProductResponse mapToProductResponse(Product product) {
    return mapToProductResponse(product, getProductQuantity(product));
  }

  public ProductResponse mapToProductResponse(Product product, Integer warehouseQuantity) {
    return ProductResponse.builder()
      .id(product.getId())
      .manufacturerName(product.getManufacturerName())
      .modelName(product.getModelName())
      .price(product.getPrice())
      .quantity(warehouseQuantity)
      .build();
  }

  public Integer getProductQuantity(Product product) {
    AtomicReference<Integer> allQuantities = new AtomicReference<>(0);

    warehouseRepository.findAll().forEach(warehouse -> {
      allQuantities.updateAndGet(v -> v + warehouse.getProducts().stream().filter(wp -> wp.getProductId().equals(product.getId())).findFirst().map(WarehouseProduct::getQuantity).orElse(0));
    });
    return allQuantities.get();
  }

}
