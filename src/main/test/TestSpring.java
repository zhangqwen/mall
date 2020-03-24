package com.zhangqw7.project.test;

import com.zhangqw7.project.service.OrderDetailService;
import com.zhangqw7.project.service.ProductService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpring {
    @Test
    public void run1() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        ProductService ps = (ProductService) ac.getBean("productService");
        ps.findAll();
    }

    @Test
    public void run2() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        OrderDetailService ods = (OrderDetailService) ac.getBean("orderDetailService");
        ods.checkOrderById(1);
    }
}
