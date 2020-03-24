package com.zhangqw7.project.service.impl;

import com.zhangqw7.project.dao.OrderDao;
import com.zhangqw7.project.model.Order;
import com.zhangqw7.project.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("orderDetailService")
public class OrderDetailServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Override
    public void setOrder(Order order) {
        System.out.println("业务层创建订单。。。");
        orderDao.setOrder(order);
    }

    @Override
    public Order checkOrderById(Integer id) {
        System.out.println("业务层查询订单by id");
        return orderDao.checkOrderById(id);
    }

    @Override
    public Order findLastOrder() {
        System.out.println("业务层查询最后一次订单");
        return  orderDao.findLastOrder();
    }

    @Override
    public List<Order> findOrderByUserId(Integer user_id) {
        System.out.println("业务层查询指定用户id订单");
        return orderDao.findOrderByUserId(user_id);
    }

    @Override
    public List<Order> findOrderByStatus(String status) {
        return orderDao.findOrderByStatus(status);
    }

    @Override
    public void updateOrderStatus(Order order) {
        orderDao.updateOrderStatus(order);
    }
}
