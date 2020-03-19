package com.zhangqw7.project.service;

import com.zhangqw7.project.model.Product;

import java.util.List;

public interface ProductService {
    public List<Product> findAll();

    public Product findById(Integer id);

    public List<Product> findByType(String type);

    public Product findByName(String name);

    public void saveProduct(Product product);

    public void updateStock(Product product);
}
