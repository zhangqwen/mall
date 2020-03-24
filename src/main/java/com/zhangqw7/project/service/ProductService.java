package com.zhangqw7.project.service;

import com.zhangqw7.project.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();

    Product findById(Integer id);

    List<Product> findByType(String type);

    List<Product> findByName(String name);

    void saveProduct(Product product);

    void updateStock(Product product);
}
