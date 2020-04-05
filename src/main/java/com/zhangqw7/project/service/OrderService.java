package com.zhangqw7.project.service;

import com.zhangqw7.project.model.Order;

import java.util.List;

public interface OrderService {

    void setOrder(Order order);

    Order checkOrderById(Integer id);

    Order findLastOrder();

    List<Order> findOrderByUserId(Integer user_id);

    List<Order> findOrderByStatus(String status);

    void updateOrderStatus(Order order);

    void deleteOrderById(Integer id);
}
