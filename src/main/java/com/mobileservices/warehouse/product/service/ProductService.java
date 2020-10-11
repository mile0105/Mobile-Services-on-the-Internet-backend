package com.mobileservices.warehouse.product.service;

import com.mobileservices.warehouse.product.model.Product;
import com.mobileservices.warehouse.product.model.ProductApi;
import com.mobileservices.warehouse.product.util.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductMapper productMapper;

    public List<Product> listAllProducts() {
        return new ArrayList<>();
    }

    public String addProduct(ProductApi productApi) {
        Product convertedProduct = productMapper.mapToDbModel(productApi);

        //todo add to db
        return String.valueOf(convertedProduct.getId());
    }

    public Product changeQuantity(int quantity, long productId) {
        Product p = Product.builder().build();
        //todo implement with repository

        return p;
    }

    public Product editProduct(long productId, ProductApi editedProduct) {

        Product product = productMapper.mapToDbModel(editedProduct);
        return product;
    }

    public void deleteProduct(long productId) {
        //todo delete
    }

}
