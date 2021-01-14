package com.mobileservices.warehouse.product.v2.model;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Set;

@Table("warehouses")
@Getter
@Builder
public class Warehouse {
  @Id
  private Long id;
  @Column("name")
  private String name;
  @Column("warehouse_id")
  private Set<WarehouseProduct> products;
}
