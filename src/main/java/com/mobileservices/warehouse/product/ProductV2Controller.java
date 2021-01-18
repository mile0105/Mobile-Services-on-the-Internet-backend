package com.mobileservices.warehouse.product;


import com.mobileservices.warehouse.product.model.Product;
import com.mobileservices.warehouse.product.model.ProductApiV1;
import com.mobileservices.warehouse.product.model.ProductApiV2;
import com.mobileservices.warehouse.product.model.ProductResponseV1;
import com.mobileservices.warehouse.product.service.ProductService;
import com.mobileservices.warehouse.product.service.ProductServiceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v2/products")
@RequiredArgsConstructor
public class ProductV2Controller {

  private final ProductServiceV2 productService;

  @GetMapping
  public ResponseEntity<List<Product>> getAllProducts() {
    final List<Product> products = productService.listAllProducts();
    return ResponseEntity.ok(products);
  }

  @PostMapping
  public ResponseEntity<Product> addProduct(@RequestBody ProductApiV2 productApiV2) {
    final Product product = productService.addProduct(productApiV2);
    return ResponseEntity.ok(product);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Product> editProduct(@PathVariable("id") Long productId, @RequestBody ProductApiV2 editedProduct) {
    Product product = productService.editProduct(productId, editedProduct);
    return ResponseEntity.ok(product);
  }
}
