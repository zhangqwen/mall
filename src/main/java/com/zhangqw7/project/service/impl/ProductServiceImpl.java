package com.zhangqw7.project.service.impl;

import com.zhangqw7.project.model.Product;
import com.zhangqw7.project.dao.IProductDao;
import com.zhangqw7.project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("productService")
public class ProductServiceImpl implements ProductService {

    @Autowired
    private IProductDao iProductDao;

    @Override
    public List<Product> findAll() {
        System.out.println("Service业务层：查询所有商品");
        return iProductDao.findAll();
    }

    @Override
    public Product findById(Integer id) {
        System.out.println("服务层查询指定id商品。。。");
        return iProductDao.findById(id);
    }

    @Override
    public List<Product> findByType(String type) {
        System.out.println("服务层查询指定类型商品。。。");
        return iProductDao.findByType(type);
    }

    @Override
    public Product findByName(String name) {
        System.out.println("服务层查询指定名称商品。。。");
        return iProductDao.findByName(name);
    }

    @Override
    public List<Product> seekByName(String name) {
        System.out.println("服务层模糊查询指定名称商品。。。");
        return iProductDao.seekByName(name);
    }

    @Override
    public void saveProduct(Product product) {
        System.out.println("Service业务层：保存商品。。。");
        iProductDao.saveProduct(product);
    }

    @Override
    public void updateProduct(Product product) {
        System.out.println("Service业务层：更新商品库存。。。");
        iProductDao.updateProduct(product);
    }

    @Override
    public List<String> getAllTypes() {
        System.out.println("Service业务层：获取所有类别。。。");
        return iProductDao.getAllTypes();
    }

    @Override
    public void deleteProductByName(String name) {
        iProductDao.deleteProductByName(name);
    }
}
