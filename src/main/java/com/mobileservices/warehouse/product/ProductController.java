package com.mobileservices.warehouse.product;

import com.mobileservices.warehouse.product.model.Product;
import com.mobileservices.warehouse.product.model.ProductApi;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    //todo implement fully

    @GetMapping("/")
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(new ArrayList<>());
    }

    @PostMapping("/")
    public ResponseEntity<Void> addProduct(ProductApi productApi) {
        return ResponseEntity.created(URI.create("/")).build();
    }

    @PutMapping("/{id}/increaseQuantity")
    public ResponseEntity<Product> increaseQuantity() {
        return ResponseEntity.ok().build();
    }


    @PutMapping("/{id}/decreaseQuantity")
    public ResponseEntity<Product> decreaseQuantity() {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct() {
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/edit")
    public ResponseEntity<Product> editProduct(ProductApi editedProduct) {
        return ResponseEntity.ok().build();
    }


}
