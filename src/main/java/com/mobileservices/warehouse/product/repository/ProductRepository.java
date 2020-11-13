package com.mobileservices.warehouse.product.repository;


import com.mobileservices.warehouse.product.model.Product;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

  @Query("UPDATE PRODUCTS SET QUANTITY = :quantity WHERE ID = :productId")
  @Modifying
  void updateProductQuantity(Long productId, Integer quantity);

}
