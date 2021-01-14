package com.mobileservices.warehouse.product.v1.model;

import com.mobileservices.warehouse.product.v2.model.WarehouseProduct;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Builder
@Table("products")
public class Product {
  @Id
  private Long id;
  @Column("manufacturer_name")
  private String manufacturerName;
  @Column("model_name")
  private String modelName;
  @Column("price")
  private Double price;
  @Column("last_updated")
  private LocalDateTime lastUpdated;
  @Column("product_id")
  private Set<WarehouseProduct> quantitiesPerWarehouse;
}
