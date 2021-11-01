package com.sivalabs.productservice.repository;

import com.sivalabs.productservice.model.Product;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository {
    private static final List<Product> PRODUCTS = new ArrayList<>();

    @PostConstruct
    void init() {
        Product p1 = new Product(1L, "Samsung TV", new BigDecimal(45000));
        Product p2 = new Product(2L, "LG Fritz", new BigDecimal(25000));
        Product p3 = new Product(3L, "Lenovo Laptop", new BigDecimal(65000));
        PRODUCTS.add(p1);
        PRODUCTS.add(p2);
        PRODUCTS.add(p3);
    }

    public List<Product> getProducts() {
        return PRODUCTS;
    }
}
