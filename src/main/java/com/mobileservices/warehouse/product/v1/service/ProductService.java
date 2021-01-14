package com.mobileservices.warehouse.product.v1.service;

import com.mobileservices.warehouse.error.exceptions.BadRequestException;
import com.mobileservices.warehouse.error.exceptions.NotFoundException;
import com.mobileservices.warehouse.product.v1.model.Product;
import com.mobileservices.warehouse.product.v1.model.ProductApi;
import com.mobileservices.warehouse.product.v1.model.ProductResponse;
import com.mobileservices.warehouse.product.v1.repository.ProductRepository;
import com.mobileservices.warehouse.product.v1.util.ProductMapper;
import com.mobileservices.warehouse.product.v2.model.WarehouseProduct;
import com.mobileservices.warehouse.product.v2.repository.WarehouseProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

  private final ProductMapper productMapper;
  private final ProductRepository productRepository;
  private final WarehouseProductRepository warehouseProductRepository;

  private final long DEFAULT_WAREHOUSE_ID = 1L;

  public List<ProductResponse> listAllProducts() {

    List<ProductResponse> products = new ArrayList<>();
    productRepository.findAll().forEach(p -> products.add(productMapper.mapToProductResponse(p)));
    return products;
  }

  public ProductResponse addProduct(ProductApi productApi) {
    Product convertedProduct = productMapper.mapToDbModelForAdding(productApi);
    Product savedProduct = productRepository.save(convertedProduct);

    return productMapper.mapToProductResponse(savedProduct);
  }

  public void changeQuantity(Long quantity, Long productId) {

    Product oldProduct = findByIdOrThrow(productId);

    int quantityValue = quantity.intValue();
    int newQuantity = productMapper.getProductQuantity(oldProduct) + quantityValue;

    if (quantityValue >= 0) {
      warehouseProductRepository.updateProductQuantity(productId, DEFAULT_WAREHOUSE_ID, newQuantity);
      return;
    }

    if (newQuantity < 0) {
      throw new BadRequestException("Quantity must not be less than 0");
    }

    long quantityCopy = -quantity;

    LinkedHashSet<WarehouseProduct> quantitiesInReversedOrder = oldProduct.getQuantitiesPerWarehouse().stream()
      .sorted(Comparator.comparingLong(WarehouseProduct::getWarehouseId).reversed())
      .collect(Collectors.toCollection(LinkedHashSet::new));

    for (WarehouseProduct warehouseProduct : quantitiesInReversedOrder) {

      Integer currentQuantity = warehouseProduct.getQuantity();
      Long currentProductId = warehouseProduct.getProductId();
      Long currentWarehouseId = warehouseProduct.getWarehouseId();

      if (quantityCopy > currentQuantity) {
        quantityCopy -= currentQuantity;
        warehouseProductRepository.updateProductQuantity(currentProductId, currentWarehouseId, 0);
      } else {
        warehouseProductRepository.updateProductQuantity(currentProductId, currentWarehouseId,
          (int)(currentQuantity - quantityCopy));
        return;
      }
    }

  }

  public ProductResponse editProduct(Long productId, ProductApi editedProduct) {

    Product oldProduct = findByIdOrThrow(productId);

    LocalDateTime editTimeStamp = editedProduct.getLastEditTimeStamp();

    if (editTimeStamp != null && oldProduct.getLastUpdated().isAfter(editTimeStamp)) {
      return productMapper.mapToProductResponse(oldProduct);
    }

    Product product = productMapper.mapToDbModelForEditing(editedProduct, productId, oldProduct.getQuantitiesPerWarehouse());
    Product savedProduct = productRepository.save(product);
    return productMapper.mapToProductResponse(savedProduct);
  }

  public void deleteProduct(Long productId) {

    findByIdOrThrow(productId);

    productRepository.deleteById(productId);
  }

  public Product findByIdOrThrow(long productId) {
    return productRepository.findById(productId).orElseThrow(() -> new NotFoundException("Product not found"));
  }
}
