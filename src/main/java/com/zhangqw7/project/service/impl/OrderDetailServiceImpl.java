package com.zhangqw7.project.service.impl;

import com.zhangqw7.project.dao.OrderDetailDao;
import com.zhangqw7.project.model.OrderDetail;
import com.zhangqw7.project.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("orderDetailService")
public class OrderDetailServiceImpl implements OrderDetailService {

    @Autowired
    private OrderDetailDao orderDetailDao;

    @Override
    public void setOrder(OrderDetail orderDetail) {
        System.out.println("业务层创建订单。。。");
        orderDetailDao.setOrder(orderDetail);
    }

    @Override
    public OrderDetail checkOrderById(Integer id) {
        System.out.println("业务层查询订单id");
        return orderDetailDao.checkOrderById(id);
    }
}
