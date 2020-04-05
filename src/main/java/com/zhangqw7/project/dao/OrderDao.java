package com.zhangqw7.project.dao;

import com.zhangqw7.project.model.Order;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDao {

    /**
     * 插入新订单
     * @param order 订单order
     */
    @Insert("insert into OrderDetail (product_id, amount, money, user_id) " +
            "value(#{product_id}, #{amount}, #{money}, #{user_id})")
    void setOrder(Order order);

    /**
     * 根据订单ID查找订单
     * @param id 订单ID
     * @return 订单
     */
    @Select("select * from OrderDetail where id=#{id}")
    Order checkOrderById(Integer id);

    /**
     * 查找最新的订单
     * @return 订单
     */
    @Select("select * from OrderDetail order by id desc limit 1")
    Order findLastOrder();

    /**
     * 根据用户ID查找所有订单
     * @param user_id 用户ID
     * @return 订单列表
     */
    @Select("select * from OrderDetail where user_id=#{user_id}")
    List<Order> findOrderByUserId(Integer user_id);

    /**
     * 根据状态status查找订单
     * @param status 订单状态
     * @return 订单列表
     */
    @Select("select * from OrderDetail where status=#{status}")
    List<Order> findOrderByStatus(String status);

    /**
     * 根据订单ID更新订单状态
     * @param order 订单ID：order.id, 订单状态：order.status
     */
    @Update("update OrderDetail set status=#{status} where id=#{id}")
    void updateOrderStatus(Order order);

    @Delete("delete from OrderDetail where id=#{id}")
    void deleteOrderById(Integer id);
}
