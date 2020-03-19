package com.zhangqw7.project.service.impl;

import com.zhangqw7.project.dao.OrderProfileDao;
import com.zhangqw7.project.model.OrderProfile;
import com.zhangqw7.project.service.OrderProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("orderProfileService")
public class OrderProfileServiceImpl implements OrderProfileService {

    @Autowired
    private OrderProfileDao orderProfileDao;

    @Override
    public void saveOrderProfile(OrderProfile orderProfile){
        System.out.println("服务层存储订单。。");
        orderProfileDao.saveOrderProfile(orderProfile);
    }
}
