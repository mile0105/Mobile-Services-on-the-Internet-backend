package com.mobileservices.warehouse.product.v2.repository;

import com.mobileservices.warehouse.product.v2.model.WarehouseProduct;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseProductRepository extends CrudRepository<WarehouseProduct, Long> {

  @Query("UPDATE WAREHOUSES_PRODUCTS SET QUANTITY = :quantity WHERE PRODUCT_ID = :productId AND WAREHOUSE_ID = :warehouseId")
  @Modifying
  void updateProductQuantity(Long productId, Integer quantity, Long warehouseId);
}
