package com.mobileservices.warehouse.product.util;

import com.mobileservices.warehouse.product.model.Product;
import com.mobileservices.warehouse.product.model.ProductApi;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    private final int STARTING_QUANTITY = 0;

    public Product mapToDbModel(ProductApi productApi, Long id) {
        return Product.builder()
                .manufacturerName(productApi.getManufacturerName())
                .modelName(productApi.getModelName())
                .price(productApi.getPrice())
                .quantity(STARTING_QUANTITY)
                .id(id)
                .build();
    }
}
