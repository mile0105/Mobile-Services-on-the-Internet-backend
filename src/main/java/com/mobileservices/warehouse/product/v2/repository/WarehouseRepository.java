package com.mobileservices.warehouse.product.v2.repository;

import com.mobileservices.warehouse.product.v2.model.Warehouse;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseRepository extends CrudRepository<Warehouse, Long> {

}
