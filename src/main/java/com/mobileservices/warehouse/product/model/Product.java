package com.mobileservices.warehouse.product.model;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

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
    @Column("quantity")
    private Integer quantity;
}
