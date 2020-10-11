package com.mobileservices.warehouse.product;

import com.mobileservices.warehouse.product.model.Product;
import com.mobileservices.warehouse.product.model.ProductApi;
import com.mobileservices.warehouse.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    //todo implement fully

    @GetMapping("/")
    public ResponseEntity<List<Product>> getAllProducts() {
        final List<Product> products = productService.listAllProducts();
        return ResponseEntity.ok(products);
    }

    @PostMapping("/")
    public ResponseEntity<Void> addProduct(ProductApi productApi) {
        final String productId = productService.addProduct(productApi);
        return ResponseEntity.created(URI.create(productId)).build();
    }

    @PutMapping("/{id}/increaseQuantity")
    public ResponseEntity<Product> increaseQuantity(@RequestParam("id") long productId, long quantity) {
        return ResponseEntity.ok().build();
    }


    @PutMapping("/{id}/decreaseQuantity")
    public ResponseEntity<Product> decreaseQuantity(@RequestParam("id") long productId, long quantity) {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@RequestParam("id") long productId) {
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/edit")
    public ResponseEntity<Product> editProduct(@RequestParam("id") long productId, ProductApi editedProduct) {
        return ResponseEntity.ok().build();
    }
}
