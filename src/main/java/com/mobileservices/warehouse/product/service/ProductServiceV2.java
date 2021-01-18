package com.mobileservices.warehouse.product.service;

import com.mobileservices.warehouse.error.exceptions.BadRequestException;
import com.mobileservices.warehouse.error.exceptions.NotFoundException;
import com.mobileservices.warehouse.product.model.Product;
import com.mobileservices.warehouse.product.model.ProductApiV2;
import com.mobileservices.warehouse.product.repository.ProductRepository;
import com.mobileservices.warehouse.product.util.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceV2 {

  private final ProductMapper productMapper;
  private final ProductRepository productRepository;

  private final int STARTING_QUANTITY = 0;

  public List<Product> listAllProducts() {

    List<Product> products = new ArrayList<>();
    productRepository.findAll().forEach(products::add);
    return products;
  }

  public Product addProduct(ProductApiV2 productApiV2) {
    Product convertedProduct = productMapper.mapToDbModel(productApiV2, null, STARTING_QUANTITY);

    Product savedProduct = productRepository.save(convertedProduct);

    return savedProduct;
  }

  public Product editProduct(Long productId, ProductApiV2 editedProduct) {

    Product oldProduct = findByIdOrThrow(productId);

    LocalDateTime editTimeStamp = editedProduct.getLastEditTimeStamp();

    if (editTimeStamp != null && oldProduct.getLastUpdated().isAfter(editTimeStamp)) {
      return oldProduct;
    }

    Product product = productMapper.mapToDbModel(editedProduct, productId, oldProduct.getQuantity());
    return productRepository.save(product);
  }

  private Product findByIdOrThrow(long productId) {
    return productRepository.findById(productId).orElseThrow(() -> new NotFoundException("Product not found"));
  }
}
