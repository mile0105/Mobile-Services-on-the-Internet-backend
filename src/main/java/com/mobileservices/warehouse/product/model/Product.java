package com.mobileservices.warehouse.product.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {
    private long id;
    private String manufacturerName;
    private String modelName;
    private double price;
    private int quantity;
}
