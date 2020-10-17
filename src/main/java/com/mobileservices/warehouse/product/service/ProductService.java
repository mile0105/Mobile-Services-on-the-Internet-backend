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

  public List<Product> listAllProducts() {

    List<Product> products = new ArrayList<>();
    productRepository.findAll().forEach(products::add);
    return products;
  }

  public String addProduct(ProductApi productApi) {
    Product convertedProduct = productMapper.mapToDbModel(productApi, null);

    Product savedProduct = productRepository.save(convertedProduct);

    return String.valueOf(savedProduct.getId());
  }

  public Product changeQuantity(Long quantity, Long productId) {
    Product p = Product.builder().build();
    //todo implement with repository

    return p;
  }

  public Product editProduct(Long productId, ProductApi editedProduct) {

    Product product = productMapper.mapToDbModel(editedProduct, productId);
    return productRepository.save(product);
  }

  public void deleteProduct(Long productId) {
    productRepository.deleteById(productId);
  }

}
