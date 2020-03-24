package com.zhangqw7.project.controller;

import com.zhangqw7.project.model.Order;
import com.zhangqw7.project.model.Product;
import com.zhangqw7.project.model.User;
import com.zhangqw7.project.service.OrderService;
import com.zhangqw7.project.service.OrderProfileService;
import com.zhangqw7.project.service.ProductService;
import com.zhangqw7.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderProfileService orderProfileService;

    @RequestMapping("/order/confirmOrder")
    public String confirmOrder(@ModelAttribute Order order, Model model, HttpServletRequest request) {

        //获取session
        HttpSession session = request.getSession(true);

        //通过用户输入的商品名称获取数据库中该商品详细信息
        Integer pid = order.getProduct_id();
        Product product = productService.findById(pid);

        //获取用户的购买数量
        Integer amount = order.getAmount();

        //比较购买数量和库存
        Integer stock = product.getStock();
        if(stock >= amount) {
            //设置订单详情
            order.setMoney(amount*product.getPrice());

            Integer user_id = (Integer) session.getAttribute("session");
            order.setUser_id(user_id);

            //写入订单数据库
            orderService.setOrder(order);
            System.out.println("订单入成功。。。");
            Order od = orderService.findLastOrder();
            Integer order_id = od.getId();
            //使用session记录订单号
            session.setAttribute("order_id", order_id);

            //跳转页面
            model.addAttribute("order", od);
            model.addAttribute("productService", productService);
            return "orderCheck";
        }else {
            System.out.println("该商品"+ product.getName() + "货存不足，请重新选购");
            return "catalogue";
        }
    }

    @RequestMapping("order/confirmUser")
    public String confirmUser(@ModelAttribute User us, HttpServletRequest request) {

        //获取session中的用户id和订单id
        HttpSession session = request.getSession(true);
        //Integer id = session.getAttribute("session") != null?(Integer) session.getAttribute("session"):0;
        Integer user_id = (Integer) session.getAttribute("session");
        Integer order_id = (Integer) session.getAttribute("order_id");

        //查询已登录用户账号信息
        User user = userService.findUserById(user_id);

        //比较密码
        if(user != null) {
            if(user.getPassword().equals(us.getPassword())){

                System.out.println("身份验证成功！");

                //更新商家订单信息并存入数据库
//                OrderProfile orderProfile = new OrderProfile();
//                orderProfile.setOrder_id(order_id);
//                orderProfile.setUser_id(user.getId());
//                orderProfile.setOrder_time(LocalDateTime.now());
//                orderProfile.setStatus(true);
//                orderProfileService.saveOrderProfile(orderProfile);

                //更新商品存货情况
                Order order = orderService.checkOrderById(order_id);
                Product product = productService.findById(order.getProduct_id());
                product.setStock(product.getStock()- order.getAmount());
                productService.updateStock(product);

                //转向致谢页面
                return "thanks";
            } else {
                System.out.println("密码错误。。。");
                return "catalogue";
            }
        }else {
            System.out.println("账号不存在。。。");
            return "catalogue";
        }
    }

    @RequestMapping("/order/list")
    public String orderProcess(Model model){
        List<Order> orders = orderService.findOrderByStatus("untreated");
        model.addAttribute("untreated_orders", orders);

        model.addAttribute("productService", productService);
        return "orderList";
    }

    @RequestMapping("/order/status/{status}")
    public String rejectOrder(@PathVariable("status") String status, @RequestParam("order_id") Integer order_id, Model model) {
        Order order = orderService.checkOrderById(order_id);
        order.setStatus(status + "ed");
        orderService.updateOrderStatus(order);

        List<Order> orders = orderService.findOrderByStatus("untreated");
        model.addAttribute("untreated_orders", orders);

        model.addAttribute("productService", productService);
        return "orderList";
    }

    /*
        @RequestMapping("/order/accept")
    public String acceptOrder(@RequestParam("order_id") Integer order_id, Model model) {
        Order order = orderService.checkOrderById(order_id);
        order.setStatus("accepted");
        orderService.updateOrderStatus(order);

        List<Order> orders = orderService.findOrderByStatus("untreated");
        model.addAttribute("untreated_orders", orders);

        model.addAttribute("productService", productService);
        return "orderList";
    }

    @RequestMapping(value="/order", method= RequestMethod.GET)
    public String checkOrderById(
            @RequestParam("id") Integer id, Model model) {
        System.out.println("表现层查询订单。。。");

        Order od = orderDetailService.checkOrderById(id);
        model.addAttribute("od", od);
        return "pages/order";
    }

    @RequestMapping(value = "/createOrder", method = RequestMethod.POST)
    public void setOrder(Order order, HttpServletRequest request, HttpServletResponse response) throws IOException {
        orderDetailService.setOrder(order);
        response.sendRedirect(request.getContextPath()+"/order");
        return;
    }
    */
}
