package com.mobileservices.warehouse.product;

import com.mobileservices.warehouse.product.model.Product;
import com.mobileservices.warehouse.product.model.ProductApi;
import com.mobileservices.warehouse.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
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
    public ResponseEntity<Void> addProduct(@RequestBody ProductApi productApi) {
        final String productId = productService.addProduct(productApi);
        return ResponseEntity.created(URI.create(productId)).build();
    }

    @PutMapping("/{id}/increaseQuantity")
    public ResponseEntity<Product> increaseQuantity(@PathVariable("id") long productId, @RequestBody Long quantity) {

        Product product = productService.changeQuantity(quantity, productId);
        return ResponseEntity.ok(product);
    }


    @PutMapping("/{id}/decreaseQuantity")
    public ResponseEntity<Product> decreaseQuantity(@PathVariable("id") long productId, @RequestBody long quantity) {
        Product product = productService.changeQuantity(-quantity, productId);
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") long productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/edit")
    public ResponseEntity<Product> editProduct(@PathVariable("id") Long productId, ProductApi editedProduct) {
        Product product = productService.editProduct(productId, editedProduct);
        return ResponseEntity.ok(product);
    }
}
