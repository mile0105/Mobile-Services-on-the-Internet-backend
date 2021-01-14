package com.mobileservices.warehouse.product.v2;

import com.mobileservices.warehouse.product.v1.model.Product;
import com.mobileservices.warehouse.product.v2.model.Warehouse;
import com.mobileservices.warehouse.product.v2.service.WarehouseService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v2/warehouses")
@RequiredArgsConstructor
public class WarehouseController {

  private final WarehouseService warehouseService;

  @GetMapping("/{id}")
  public ResponseEntity<Warehouse> getWarehouse(@PathVariable("id") long warehouseId) {
    Warehouse warehouse = warehouseService.getWarehouse(warehouseId);
    return ResponseEntity.ok(warehouse);
  }

  @GetMapping
  public ResponseEntity<List<Warehouse>> getAllWarehouses() {
    return ResponseEntity.ok(warehouseService.getWarehouses());
  }

  /*
  @PostMapping
  public ResponseEntity<Product> addProduct(@RequestBody ProductApi productApi) {
    final Product product = productV2Service.addProduct(productApi);
    return ResponseEntity.ok(product);
  }

  @PatchMapping(value = "/{id}/quantity")
  public ResponseEntity<EmptyResponse> changeQuantity(@PathVariable("id") long productId, @RequestBody Long quantity) {
    productV2Service.changeQuantity(quantity, productId);
    return ResponseEntity.ok(new EmptyResponse());
  }

  @DeleteMapping("/{id}")
  @PreAuthorize("hasAuthority('MANAGER')")
  public ResponseEntity<EmptyResponse> deleteProduct(@PathVariable("id") long productId) {
    productV2Service.deleteProduct(productId);
    return ResponseEntity.ok(new EmptyResponse());
  }

  @PutMapping("/{id}")
  public ResponseEntity<Product> editProduct(@PathVariable("id") Long productId, @RequestBody ProductApi editedProduct) {
    Product product = productV2Service.editProduct(productId, editedProduct);
    return ResponseEntity.ok(product);
  }*/
}
