package com.mobileservices.warehouse.product.v2.util;

import com.mobileservices.warehouse.product.v1.model.Product;
import com.mobileservices.warehouse.product.v1.model.ProductResponse;
import com.mobileservices.warehouse.product.v1.service.ProductService;
import com.mobileservices.warehouse.product.v1.util.ProductMapper;
import com.mobileservices.warehouse.product.v2.model.Warehouse;
import com.mobileservices.warehouse.product.v2.model.WarehouseProduct;
import com.mobileservices.warehouse.product.v2.model.WarehouseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class WarehouseMapper {

  private final ProductService productService;
  private final ProductMapper productMapper;

  public WarehouseResponse mapToResponse(Warehouse warehouse) {

    Set<ProductResponse> responses = new HashSet<>();

    for (WarehouseProduct warehouseProduct: warehouse.getProducts()) {
      Long productId = warehouseProduct.getProductId();
      Product product = productService.findByIdOrThrow(productId);
      responses.add(productMapper.mapToProductResponse(product, warehouseProduct.getQuantity()));
    }

    return WarehouseResponse.builder()
      .id(warehouse.getId())
      .name(warehouse.getName())
      .products(responses)
      .build();
  }
}
