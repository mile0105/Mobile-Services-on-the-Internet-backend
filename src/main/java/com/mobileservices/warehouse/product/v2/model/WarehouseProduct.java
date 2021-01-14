package com.mobileservices.warehouse.product.v2.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Builder
@AllArgsConstructor
@Table("warehouses_products")
public class WarehouseProduct {
  @Id
  @Column("warehouse_id")
  private Long warehouseId;
  @Column("product_id")
  private Long productId;
  @Column("quantity")
  private Integer quantity;
}
