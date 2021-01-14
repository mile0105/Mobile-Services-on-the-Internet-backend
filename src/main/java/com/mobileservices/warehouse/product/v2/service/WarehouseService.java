package com.mobileservices.warehouse.product.v2.service;

import com.mobileservices.warehouse.error.exceptions.BadRequestException;
import com.mobileservices.warehouse.error.exceptions.NotFoundException;
import com.mobileservices.warehouse.product.v1.model.Product;
import com.mobileservices.warehouse.product.v1.model.ProductApi;
import com.mobileservices.warehouse.product.v1.repository.ProductRepository;
import com.mobileservices.warehouse.product.v1.service.ProductService;
import com.mobileservices.warehouse.product.v2.model.Warehouse;
import com.mobileservices.warehouse.product.v2.model.WarehouseResponse;
import com.mobileservices.warehouse.product.v2.repository.WarehouseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WarehouseService {

  private final WarehouseRepository warehouseRepository;
  private final ProductService productService;

  public Warehouse getWarehouse(Long warehouseId) {
    return findByIdOrThrow(warehouseId);
  }

  public List<Warehouse> getWarehouses() {
    List<Warehouse> warehouses = new ArrayList<>();
    warehouseRepository.findAll().forEach(warehouses::add);
    return warehouses;
  }
/*
  public Product addProduct(ProductApi productApi) {
    Product convertedProduct = productMapper.mapToDbModel(productApi, null, STARTING_QUANTITY);

    Product savedProduct = productRepository.save(convertedProduct);

    return savedProduct;
  }

  public void changeQuantity(Long quantity, Long productId) {

    Product oldProduct = findByIdOrThrow(productId);

    int newQuantity = quantity.intValue() + oldProduct.getQuantity();

    if (newQuantity < 0) {
      throw new BadRequestException("Quantity must not be less than 0");
    }
    productRepository.updateProductQuantity(productId, newQuantity);
  }

  public Product editProduct(Long productId, ProductApi editedProduct) {

    Product oldProduct = findByIdOrThrow(productId);

    LocalDateTime editTimeStamp = editedProduct.getLastEditTimeStamp();

    if (editTimeStamp != null && oldProduct.getLastUpdated().isAfter(editTimeStamp)) {
      return oldProduct;
    }

    Product product = productMapper.mapToDbModel(editedProduct, productId, oldProduct.getQuantity());
    return productRepository.save(product);
  }

  public void deleteProduct(Long productId) {

    findByIdOrThrow(productId);

    productRepository.deleteById(productId);
  }
*/
  private Warehouse findByIdOrThrow(long warehouseId) {
    return warehouseRepository.findById(warehouseId).orElseThrow(() -> new NotFoundException("Warehouse not found"));
  }
}
