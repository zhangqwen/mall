package com.zhangqw7.project.dao;

import com.zhangqw7.project.model.OrderProfile;
import org.apache.ibatis.annotations.Insert;

public interface OrderProfileDao {

    @Insert("insert into OrderProfile (order_time, order_id, status, user_id) " +
            "value(#{order_time}, #{order_id}, #{status}, #{user_id})")
    public void saveOrderProfile(OrderProfile orderProfile);
}
