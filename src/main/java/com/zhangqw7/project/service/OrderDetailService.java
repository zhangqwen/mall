package com.zhangqw7.project.service;

import com.zhangqw7.project.model.OrderDetail;

public interface OrderDetailService {

    public void setOrder(OrderDetail orderDetail);

    public OrderDetail checkOrderById(Integer id);
}
