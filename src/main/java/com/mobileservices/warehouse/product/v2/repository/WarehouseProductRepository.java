package com.mobileservices.warehouse.product.v2.repository;

import com.mobileservices.warehouse.product.v2.model.WarehouseProduct;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WarehouseProductRepository extends CrudRepository<WarehouseProduct, Long> {

  @Query("UPDATE WAREHOUSES_PRODUCTS SET QUANTITY = :quantity WHERE PRODUCT_ID = :productId AND WAREHOUSE_ID = :warehouseId")
  @Modifying
  void updateProductQuantity(Long productId, Long warehouseId, Integer quantity);

  @Query("SELECT * FROM WAREHOUSES_PRODUCTS WHERE PRODUCT_ID = :productId AND WAREHOUSE_ID = :warehouseId")
  Optional<WarehouseProduct> findById(Long warehouseId, Long productId);
}
