package com.mobileservices.warehouse.product.v1;

import com.mobileservices.warehouse.product.v1.model.Product;
import com.mobileservices.warehouse.product.v1.model.ProductApi;
import com.mobileservices.warehouse.product.v1.model.ProductResponse;
import com.mobileservices.warehouse.product.v1.service.ProductService;
import com.mobileservices.warehouse.util.models.EmptyResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

  private final ProductService productService;

  @GetMapping
  public ResponseEntity<List<ProductResponse>> getAllProducts() {
    return ResponseEntity.ok(productService.listAllProducts());
  }

  @PostMapping
  public ResponseEntity<ProductResponse> addProduct(@RequestBody ProductApi productApi) {
    return ResponseEntity.ok(productService.addProduct(productApi));
  }

  @PatchMapping(value = "/{id}/quantity")
  public ResponseEntity<EmptyResponse> changeQuantity(@PathVariable("id") long productId, @RequestBody Long quantity) {
    productService.changeQuantity(quantity, productId);
    return ResponseEntity.ok(new EmptyResponse());
  }

  @DeleteMapping("/{id}")
  @PreAuthorize("hasAuthority('MANAGER')")
  public ResponseEntity<EmptyResponse> deleteProduct(@PathVariable("id") long productId) {
    productService.deleteProduct(productId);
    return ResponseEntity.ok(new EmptyResponse());
  }

  @PutMapping("/{id}")
  public ResponseEntity<ProductResponse> editProduct(@PathVariable("id") Long productId, @RequestBody ProductApi editedProduct) {
    return ResponseEntity.ok(productService.editProduct(productId, editedProduct));
  }
}
