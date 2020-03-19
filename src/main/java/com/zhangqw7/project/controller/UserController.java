package com.zhangqw7.project.controller;

import com.zhangqw7.project.model.User;
import com.zhangqw7.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.UUID;

@Controller
//@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

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
}
