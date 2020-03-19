package com.zhangqw7.project.controller;

import com.zhangqw7.project.model.OrderDetail;
import com.zhangqw7.project.model.OrderProfile;
import com.zhangqw7.project.model.Product;
import com.zhangqw7.project.model.User;
import com.zhangqw7.project.service.OrderDetailService;
import com.zhangqw7.project.service.OrderProfileService;
import com.zhangqw7.project.service.ProductService;
import com.zhangqw7.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class OrderDetailController {

    @Autowired
    private OrderDetailService orderDetailService;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderProfileService orderProfileService;

    @RequestMapping("/order/confirmOrder")
    public String confirmOrder(@ModelAttribute OrderDetail orderDetail, Model model, HttpServletRequest request) {

        //使用session记录订单号


        //通过用户输入的商品名称获取数据库中该商品详细信息
        Product product = productService.findByName(orderDetail.getProduct_name());

        //获取用户的购买数量
        Integer amount = orderDetail.getAmount();

        //比较购买数量和库存
        if(product.getStock() >= amount) {
            orderDetail.setMoney(amount*product.getPrice());
            orderDetail.setProduct_id(product.getId());
            orderDetailService.setOrder(orderDetail);
        }else {
            System.out.println("该商品"+ product.getName() + "货存不足，请重新选购");
            return "orderForm";
        }
        model.addAttribute("orderDetail", orderDetail);
        return "orderCheck";
    }

    @RequestMapping("order/confirmUser")
    public String confirmUser(@ModelAttribute User us,Integer order_id, HttpServletRequest request) {

        //获取session中的用户id
        HttpSession session = request.getSession(true);
        //Integer id = session.getAttribute("session") != null?(Integer) session.getAttribute("session"):0;
        Integer id = (Integer) session.getAttribute("session");
        User user = userService.findUserById(id);

        //比较密码
        if(user != null) {
            if(user.getPassword().equals(us.getPassword())){

                System.out.println("身份验证成功！");

                //更新订单信息并存入数据库
                OrderProfile orderProfile = new OrderProfile();
                orderProfile.setOrder_id(order_id);
                orderProfile.setUser_id(user.getId());
                orderProfile.setOrder_time(LocalDateTime.now());
                orderProfile.setStatus(true);
                orderProfileService.saveOrderProfile(orderProfile);

                //更新商品存货情况
                OrderDetail orderDetail = orderDetailService.checkOrderById(order_id);
                Product product = productService.findById(orderDetail.getProduct_id());
                product.setStock(product.getStock()-orderDetail.getAmount());
                productService.updateStock(product);

                //转向致谢页面
                return "thanks";
            } else {
                System.out.println("密码错误。。。");
                return "orderForm";
            }
        }else {
            System.out.println("账号不存在。。。");
            return "orderForm";
        }
    }
    /*
    @RequestMapping(value="/orderDetail", method= RequestMethod.GET)
    public String checkOrderById(
            @RequestParam("id") Integer id, Model model) {
        System.out.println("表现层查询订单。。。");

        OrderDetail od = orderDetailService.checkOrderById(id);
        model.addAttribute("od", od);
        return "pages/order";
    }

    @RequestMapping(value = "/createOrder", method = RequestMethod.POST)
    public void setOrder(OrderDetail orderDetail, HttpServletRequest request, HttpServletResponse response) throws IOException {
        orderDetailService.setOrder(orderDetail);
        response.sendRedirect(request.getContextPath()+"/orderDetail");
        return;
    }
    */
}
