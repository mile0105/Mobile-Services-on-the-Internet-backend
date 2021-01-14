package com.mobileservices.warehouse.product.v2;

import com.mobileservices.warehouse.product.v2.model.Warehouse;
import com.mobileservices.warehouse.product.v2.model.WarehouseResponse;
import com.mobileservices.warehouse.product.v2.service.WarehouseService;
import com.mobileservices.warehouse.util.models.EmptyResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v2/warehouses")
@RequiredArgsConstructor
public class WarehouseController {

  private final WarehouseService warehouseService;

  @GetMapping("/{id}")
  public ResponseEntity<WarehouseResponse> getWarehouse(@PathVariable("id") long warehouseId) {
    return ResponseEntity.ok(warehouseService.getWarehouse(warehouseId));
  }

  @GetMapping
  public ResponseEntity<List<WarehouseResponse>> getAllWarehouses() {
    return ResponseEntity.ok(warehouseService.getWarehouses());
  }

  @PatchMapping(value = "/{warehouseId}/products/{productId}/quantity")
  public ResponseEntity<EmptyResponse> changeQuantity(@PathVariable("warehouseId") long warehouseId,
                                                      @PathVariable("productId") long productId,
                                                      @RequestBody Long quantity) {
    warehouseService.updateQuantity(warehouseId, productId, quantity);
    return ResponseEntity.ok(new EmptyResponse());
  }
}
