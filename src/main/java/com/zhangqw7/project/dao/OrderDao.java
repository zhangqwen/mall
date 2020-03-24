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

    //插入新订单
    @Insert("insert into OrderDetail (product_id, amount, money, user_id) " +
            "value(#{product_id}, #{amount}, #{money}, #{user_id})")
    public void setOrder(Order order);

    //查找指定id订单
    @Select("select * from OrderDetail where id=#{id}")
    public Order checkOrderById(Integer id);

    //查找最新插入的订单
    @Select("select * from OrderDetail order by id desc limit 1")
    public Order findLastOrder();

    //查找指定用户id订单
    @Select("select * from OrderDetail where user_id=#{user_id}")
    public List<Order> findOrderByUserId(Integer user_id);

    //查找指定状态订单
    @Select("select * from OrderDetail where status=#{status}")
    public List<Order> findOrderByStatus(String status);

    @Update("update OrderDetail set status=#{status} where id=#{id}")
    public void updateOrderStatus(Order order);
}
