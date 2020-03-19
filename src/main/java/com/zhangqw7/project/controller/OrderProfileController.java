package com.zhangqw7.project.controller;

import com.zhangqw7.project.service.OrderProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OrderProfileController {

    @Autowired
    private OrderProfileService orderProfileService;

//    @RequestMapping("/order/payment")
    //public String pay()
}
