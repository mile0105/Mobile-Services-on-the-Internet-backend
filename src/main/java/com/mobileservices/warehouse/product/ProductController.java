package com.mobileservices.warehouse.product;

import com.mobileservices.warehouse.product.model.Product;
import com.mobileservices.warehouse.product.model.ProductApiV1;
import com.mobileservices.warehouse.product.model.ProductResponseV1;
import com.mobileservices.warehouse.product.service.ProductService;
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
  public ResponseEntity<List<ProductResponseV1>> getAllProducts() {
    final List<ProductResponseV1> products = productService.listAllProducts();
    return ResponseEntity.ok(products);
  }

  @PostMapping
  public ResponseEntity<ProductResponseV1> addProduct(@RequestBody ProductApiV1 productApi) {
    final ProductResponseV1 product = productService.addProduct(productApi);
    return ResponseEntity.ok(product);
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
  public ResponseEntity<ProductResponseV1> editProduct(@PathVariable("id") Long productId, @RequestBody ProductApiV1 editedProduct) {
    ProductResponseV1 product = productService.editProduct(productId, editedProduct);
    return ResponseEntity.ok(product);
  }
}
