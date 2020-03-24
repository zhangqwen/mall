package com.zhangqw7.project.controller;

import com.zhangqw7.project.model.Order;
import com.zhangqw7.project.model.User;
import com.zhangqw7.project.service.OrderService;
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
import java.util.List;
import java.util.UUID;

@Controller
//@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    @RequestMapping("/user/registering")
    public String registering() {
        return "registerForm";
    }

/*
    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    public void register(@ModelAttribute User user, HttpServletRequest request, HttpServletResponse response) throws IOException {
        userService.register(user);
        response.sendRedirect(request.getContextPath() + "/../../product/catalog");
        return;
    }
    */

    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    public String register(@ModelAttribute User us) {
        User user = userService.findUserByName(us.getUsername());
        if(user == null){
            userService.register(us);
            System.out.println("注册成功");
            return "catalogue";
        }else {
            System.out.println("用户名已存在，请更改用户名。。。");
            return "registerForm";
        }
    }

    @RequestMapping("/user/index")
    public String main() {
        return "../../index";
    }

    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    public String login(@ModelAttribute User us, HttpServletRequest request) {
//     public String doLogin(@RequestBody String username,String password) {
        //String username = request.getParameter("username");
        //String password = request.getParameter("password");
        User user = userService.findUserByName(us.getUsername());

//        System.out.println("系统路径为"+request.getContextPath());
//        System.out.println("Servlet路径为"+request.getServletPath());


        // UUID.randomUUID()

        if(user != null) {
            if(user.getPassword().equals(us.getPassword())) {
                System.out.println("登录成功");

                HttpSession session = request.getSession(true);
                session.setAttribute("session", userService.findUserByName(us.getUsername()).getId());

                return "catalogue";
            }else {
                System.out.println("密码错误，请重新登录");
                return "../../index";
            }
        }else {
            System.out.println("用户不存在，请注册。。。");
            return "registerForm";
        }
    }

    @RequestMapping("/user/setting")
    public String setting() {
        return "userSetting";
    }

    @RequestMapping("/user/cancel")
    public String cancel(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Integer user_id = (Integer) session.getAttribute("session");
        User user = userService.findUserById(user_id);
        userService.cancel(user);
        session.removeAttribute("session");
        System.out.println("控制器注销用户");
        return "../../index";
    }

    @RequestMapping("/user/reset")
    public String reset() {
        return "userReset";
    }


    @RequestMapping("/user/update")
    public String update(@RequestParam("password") String password, HttpServletRequest request) {

//        System.out.println("Servlet路径为"+request.getServletPath());

        HttpSession session = request.getSession();
        Integer user_id = (Integer) session.getAttribute("session");
        User user = userService.findUserById(user_id);
        user.setPassword(password);
        userService.update(user);
        System.out.println("控制层修改密码成功");
        return "catalogue";
    }

    @RequestMapping("/user/orders")
    public String viewOrders(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        Integer user_id = (Integer) session.getAttribute("session");

        List<Order> orders = orderService.findOrderByUserId(user_id);
        model.addAttribute("orders", orders);

        model.addAttribute("productService", productService);

        return "userOrders";
    }

    @RequestMapping("/admin/login")
    public String adminLogin() {
        return "adminLogin";
    }

    @RequestMapping("/admin/setting")
    public String adminSetting(String username, String password, Model model, HttpServletRequest request) {
        User admin = userService.findUserByName(username);
        if(admin == null){
            System.out.println("系统管理员账号错误。。。");
            return "adminLogin";
        }
        if(admin.getPassword().equals(password)){
            if(admin.getId()==1){
                List<User> users = userService.findAllUser();
                model.addAttribute("users", users);

                HttpSession session = request.getSession(true);
                session.setAttribute("session", admin.getId());
                return "adminSetting";
            }else if(admin.getId()==19){
                return "saler";
            }else {
                return "../../index";
            }
        }else {
            System.out.println("系统管理员密码错误。。。");
            return "adminLogin";
        }
    }

    @RequestMapping("/admin/saler")
    public String backToSaler() {
        return "saler";
    }

    @RequestMapping("/admin/returnSetting")
    public String returnSetting(Model model) {
        List<User> users = userService.findAllUser();
        model.addAttribute("users", users);
        return "adminSetting";
    }

    @RequestMapping("/admin/deleteUser")
    public String deleteUser(Integer user_id, Model model) {
        userService.deleteUserById(user_id);

        List<User> users = userService.findAllUser();
        model.addAttribute("users", users);
        return "adminSetting";
    }

    @RequestMapping("/admin/addUser")
    public String addUser() {
        return "addUser";
    }

    @RequestMapping("/admin/addedUser")
    public String addedUser(@ModelAttribute User us, Model model){
        User user = userService.findUserByName(us.getUsername());
        User user_ = userService.findUserById(us.getId());
        if(user == null){
            if(user_ == null) {
                userService.register(us);
                System.out.println("注册成功");
                return "addUser";
            }else {
                System.out.println("用户ID已存在，请更改用户ID。。。");
                return "adminSetting";
            }
        }else {
            System.out.println("用户名已存在，请更改用户名。。。");
            return "adminSetting";
        }
    }
}
