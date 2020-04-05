package com.zhangqw7.project.controller;

import com.alibaba.fastjson.JSONObject;
import com.zhangqw7.project.constant.RespCode;
import com.zhangqw7.project.model.Order;
import com.zhangqw7.project.model.Product;
import com.zhangqw7.project.model.User;
import com.zhangqw7.project.service.OrderService;
import com.zhangqw7.project.service.ProductService;
import com.zhangqw7.project.service.UserService;
import com.zhangqw7.project.utils.ResponseJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    /**
     * 购买商品提交订单
     * @param order 必须提供order.user_id, order.product_id, order.amount
     * @return data: order_id
     */
    @RequestMapping("/order/create")
    @ResponseBody
    public String createOrder(@RequestBody Order order) {
        Integer amount = order.getAmount();
        Integer product_id = order.getProduct_id();
        Product product = productService.findById(product_id);

        if (amount <= product.getStock()) {

            Double money = amount * product.getPrice();
            order.setMoney(money);
//            order.setStatus("untreated");
            orderService.setOrder(order);

            //更新库存
            product.setStock(product.getStock() - amount);
            productService.updateProduct(product);

//            Integer order_id = orderService.getLastId();
            Integer order_id = orderService.findLastOrder().getId();

            ResponseJson rm = new ResponseJson(RespCode.CODE_1000, "购买成功！", order_id);
            return JSONObject.toJSONString(rm.toMap());
        } else {
            ResponseJson rm = new ResponseJson(RespCode.CODE_1001, "商品太火爆了，库存不足请减少数量或选购其它商品");
            return JSONObject.toJSONString(rm.toMap());
        }

    }

    /**
     * 查找指定用户ID对应的所有订单
     * @param user user.id
     * @return data: orders
     */
    @RequestMapping("/order/searches")
    @ResponseBody
    public String getOrdersByUserId(@RequestBody User user) {
        List<Order> orders = orderService.findOrderByUserId(user.getId());
        if (orders.isEmpty()) {
            ResponseJson rm = new ResponseJson(RespCode.CODE_1001, "未查找到相关订单");
            return JSONObject.toJSONString(rm.toMap());
        } else {
            ResponseJson rm = new ResponseJson(RespCode.CODE_1000, "查询用户订单成功", orders);
            return JSONObject.toJSONString(rm.toMap());
        }
    }

    /**
     * 查找所有未处理的订单
     * @return data: orders
     */
    @RequestMapping("/order/list")
    @ResponseBody
    public String orderProcess() {
        List<Order> orders = orderService.findOrderByStatus("untreated");
        if (orders != null) {
            ResponseJson rm = new ResponseJson(RespCode.CODE_1000, "未处理订单查询成功", orders);
            return JSONObject.toJSONString(rm.toMap());
        } else {
            ResponseJson rm = new ResponseJson(RespCode.CODE_1000, "没有未处理的订单");
            return JSONObject.toJSONString(rm.toMap());
        }

    }

    /**
     * 接受指定ID的订单
     * @param order order.id
     * @return data: order.id
     */
    @RequestMapping("/order/accept")
    @ResponseBody
    public String acceptOrder(@RequestBody Order order) {
        Order od = orderService.checkOrderById(order.getId());
        od.setStatus("accepted");
        orderService.updateOrderStatus(od);
        ResponseJson rm = new ResponseJson(RespCode.CODE_1000, "已接受订单", od);
        return JSONObject.toJSONString(rm.toMap());
    }

    /**
     * 拒绝指定ID的订单
     * @param order order.id
     * @return data: order.id
     */
    @RequestMapping("/order/reject")
    @ResponseBody
    public String rejectOrder(@RequestBody Order order) {
        //获取订单并修改订单状态
        Order od = orderService.checkOrderById(order.getId());

        od.setStatus("rejected");
        orderService.updateOrderStatus(od);

        //库存回滚
        Product product = productService.findById(od.getProduct_id());
        product.setStock(product.getStock() - od.getAmount());
        productService.updateProduct(product);

        ResponseJson rm = new ResponseJson(RespCode.CODE_1000, "已拒绝订单");
        return JSONObject.toJSONString(rm.toMap());
    }
}
/* 以下均由前端直接实现

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
                productService.updateProduct(product);

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

}
*/