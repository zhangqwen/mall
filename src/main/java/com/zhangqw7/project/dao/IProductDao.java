package com.zhangqw7.project.dao;

import com.zhangqw7.project.model.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductDao {

    @Select("select * from Product")
    public List<Product> findAll();

    @Select("select * from Product where type=#{id}")
    public Product findById(Integer id);

    @Select("select * from Product where type=#{type}")
    public List<Product> findByType(String type);

    @Select("select * from Product where name=#{name}")
    public Product findByName(String name);

    @Insert("insert into Product (name, stock, price, type) value(#{name}, #{stock}, #{price}, #{type})")
    public void saveProduct(Product product);

    @Update("update Product set stock=#{stock} where id=#{id}")
    public void updateStock(Product product);
}
