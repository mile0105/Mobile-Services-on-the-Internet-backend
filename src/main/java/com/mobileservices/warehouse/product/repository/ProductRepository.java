package com.mobileservices.warehouse.product.repository;


import com.mobileservices.warehouse.product.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

}
