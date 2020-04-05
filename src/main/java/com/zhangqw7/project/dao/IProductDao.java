package com.zhangqw7.project.dao;

import com.zhangqw7.project.model.Product;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductDao {

    /**
     * 查找所有商品
     * @return 商品列表
     */
    @Select("select * from Product")
    List<Product> findAll();

    /**
     * 根据商品ID查找商品
     * @param product_id 商品ID: product_id
     * @return 商品Product
     */
    @Select("select * from Product where product_id=#{product_id}")
    Product findById(Integer product_id);

    /**
     * 根据商品类型type查找商品
     * @param type 商品类型type
     * @return 商品列表
     */
    @Select("select * from Product where type=#{type}")
    List<Product> findByType(String type);

    /**
     * 根据商品名查找商品
     * @param name 商品名name
     * @return 商品Product
     */
    @Select("select * from Product where name=#{name}")
    Product findByName(String name);

    /**
     * 根据输入内容模糊查询商品
     * @param name 输入的商品名name
     * @return 商品列表
     */
    @Select("select * from Product where name like concat('%', #{name}, '%') or type like concat('%', #{name}, '%') " +
            "or detail like concat('%', #{name}, '%')")
    List<Product> seekByName(String name);

    /**
     * 新建商品
     * @param product 商品所有信息product
     */
    @Insert("insert into Product (name,detail, picture_url, stock, price, type, product_id) " +
            "value(#{name}, #{detail}, #{picture_url}, #{stock}, #{price}, #{type}, #{product_id})")
    void saveProduct(Product product);

    /**
     * 根据商品ID更新商品信息
     * @param product 必须 商品ID: product.product_id, 可选 存货product.stock, 详情prodoct.detail, 价格product.price等
     */
    @Update("update Product set stock=#{stock}, detail=#{detail}, price=#{price}, picture_url=#{picture_url} " +
            "where product_id=#{product_id}")
    void updateProduct(Product product);

    /**
     * 查找所有商品的类型种类
     * @return 类型列表
     */
    @Select("select distinct(type) from product")
    List<String> getAllTypes();

    @Delete("delete from product where name=#{name}")
    void  deleteProductByName(String name);
}
