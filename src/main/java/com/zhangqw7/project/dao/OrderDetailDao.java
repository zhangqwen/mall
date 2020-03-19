package com.zhangqw7.project.dao;

import com.zhangqw7.project.model.OrderDetail;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailDao {

    @Insert("insert into OrderDetail (product_id, product_name, amount, money, user_id) " +
            "value(#{product_id},#{product_name}, #{amount}, #{money}, #{user_id})")
    public void setOrder(OrderDetail orderDetail);

    @Select("select * from OrderDetail where id=#{id}")
    public OrderDetail checkOrderById(Integer id);
}
