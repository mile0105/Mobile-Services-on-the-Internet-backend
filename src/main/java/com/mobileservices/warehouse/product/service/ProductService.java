package com.mobileservices.warehouse.product.service;

import com.mobileservices.warehouse.product.model.Product;
import com.mobileservices.warehouse.product.model.ProductApi;
import com.mobileservices.warehouse.product.repository.ProductRepository;
import com.mobileservices.warehouse.product.util.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

  private final ProductMapper productMapper;
  private final ProductRepository productRepository;

  private final int STARTING_QUANTITY = 0;

  public List<Product> listAllProducts() {

    List<Product> products = new ArrayList<>();
    productRepository.findAll().forEach(products::add);
    return products;
  }

  public String addProduct(ProductApi productApi) {
    Product convertedProduct = productMapper.mapToDbModel(productApi, null, STARTING_QUANTITY);

    Product savedProduct = productRepository.save(convertedProduct);

    return String.valueOf(savedProduct.getId());
  }

  public void changeQuantity(Long quantity, Long productId) {

    Product oldProduct = findByIdOrThrow(productId);

    int newQuantity = quantity.intValue() + oldProduct.getQuantity();

    if (newQuantity < 0) {
      throw new IllegalStateException("Quantity must not be less than 0");
    }
    productRepository.updateProductQuantity(productId, newQuantity);
  }

  public Product editProduct(Long productId, ProductApi editedProduct) {

    Product oldProduct = findByIdOrThrow(productId);

    Product product = productMapper.mapToDbModel(editedProduct, productId, oldProduct.getQuantity());
    return productRepository.save(product);
  }

  public void deleteProduct(Long productId) {
    productRepository.deleteById(productId);
  }

  private Product findByIdOrThrow(long productId) {
    return productRepository.findById(productId).orElseThrow(() -> new IllegalStateException("Product not found"));
  }
}
