package com.zhangqw7.project.service;

import com.zhangqw7.project.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();

    Product findById(Integer id);

    List<Product> findByType(String type);

    Product findByName(String name);

    List<Product> seekByName(String name);

    void saveProduct(Product product);

    void updateProduct(Product product);

    List<String> getAllTypes();

    void  deleteProductByName(String name);
}
