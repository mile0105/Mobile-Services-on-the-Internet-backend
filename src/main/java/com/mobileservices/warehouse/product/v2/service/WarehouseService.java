package com.mobileservices.warehouse.product.v2.service;

import com.mobileservices.warehouse.error.exceptions.BadRequestException;
import com.mobileservices.warehouse.error.exceptions.NotFoundException;
import com.mobileservices.warehouse.product.v2.model.Warehouse;
import com.mobileservices.warehouse.product.v2.model.WarehouseProduct;
import com.mobileservices.warehouse.product.v2.model.WarehouseResponse;
import com.mobileservices.warehouse.product.v2.repository.WarehouseProductRepository;
import com.mobileservices.warehouse.product.v2.repository.WarehouseRepository;
import com.mobileservices.warehouse.product.v2.util.WarehouseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WarehouseService {

  private final WarehouseRepository warehouseRepository;
  private final WarehouseProductRepository warehouseProductRepository;
  private final WarehouseMapper warehouseMapper;

  public WarehouseResponse getWarehouse(Long warehouseId) {
    Warehouse warehouse = findByIdOrThrow(warehouseId);
    return warehouseMapper.mapToResponse(warehouse);
  }

  public List<WarehouseResponse> getWarehouses() {
    List<WarehouseResponse> warehouses = new ArrayList<>();
    warehouseRepository.findAll().forEach(warehouse -> warehouses.add(warehouseMapper.mapToResponse(warehouse)));
    return warehouses;
  }

  public void updateQuantity(Long warehouseId, Long productId, Long quantity) {
    WarehouseProduct product = findByIdOrThrow(warehouseId, productId);

    int newQuantity = (int) (product.getQuantity() + quantity);

    if (newQuantity < 0) {
      throw new BadRequestException("Quantity must not be less than 0");
    }
    warehouseProductRepository.updateProductQuantity(productId, warehouseId, newQuantity);

  }

  private Warehouse findByIdOrThrow(long warehouseId) {
    return warehouseRepository.findById(warehouseId).orElseThrow(() -> new NotFoundException("Warehouse not found"));
  }

  private WarehouseProduct findByIdOrThrow(long warehouseId, long productId) {
    return warehouseProductRepository.findById(warehouseId, productId).orElseThrow(() -> new NotFoundException("Warehouse not found"));
  }
}
