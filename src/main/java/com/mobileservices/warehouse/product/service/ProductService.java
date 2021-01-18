package com.mobileservices.warehouse.product.service;

import com.mobileservices.warehouse.error.exceptions.BadRequestException;
import com.mobileservices.warehouse.error.exceptions.NotFoundException;
import com.mobileservices.warehouse.product.model.Product;
import com.mobileservices.warehouse.product.model.ProductApiV1;
import com.mobileservices.warehouse.product.model.ProductApiV2;
import com.mobileservices.warehouse.product.model.ProductResponseV1;
import com.mobileservices.warehouse.product.repository.ProductRepository;
import com.mobileservices.warehouse.product.util.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

  private final ProductMapper productMapper;
  private final ProductRepository productRepository;

  private final int STARTING_QUANTITY = 0;

  public List<ProductResponseV1> listAllProducts() {

    List<Product> products = new ArrayList<>();
    productRepository.findAll().forEach(products::add);
    return products.stream().map(productMapper::mapToProductResponseV1).collect(Collectors.toList());
  }

  public ProductResponseV1 addProduct(ProductApiV1 productApiV1) {
    Product convertedProduct = productMapper.mapToDbModel(productApiV1, null, STARTING_QUANTITY);

    Product savedProduct = productRepository.save(convertedProduct);

    return productMapper.mapToProductResponseV1(savedProduct);
  }

  public void changeQuantity(Long quantity, Long productId) {

    Product oldProduct = findByIdOrThrow(productId);

    int newQuantity = quantity.intValue() + oldProduct.getQuantity();

    if (newQuantity < 0) {
      throw new BadRequestException("Quantity must not be less than 0");
    }
    productRepository.updateProductQuantity(productId, newQuantity);
  }

  public ProductResponseV1 editProduct(Long productId, ProductApiV1 editedProduct) {

    Product oldProduct = findByIdOrThrow(productId);

    LocalDateTime editTimeStamp = editedProduct.getLastEditTimeStamp();

    if (editTimeStamp != null && oldProduct.getLastUpdated().isAfter(editTimeStamp)) {
      return productMapper.mapToProductResponseV1(oldProduct);
    }

    Product product = productMapper.mapToDbModel(editedProduct, productId, oldProduct.getQuantity());
    Product savedProduct = productRepository.save(product);

    return productMapper.mapToProductResponseV1(savedProduct);
  }

  public void deleteProduct(Long productId) {

    findByIdOrThrow(productId);

    productRepository.deleteById(productId);
  }

  private Product findByIdOrThrow(long productId) {
    return productRepository.findById(productId).orElseThrow(() -> new NotFoundException("Product not found"));
  }
}
