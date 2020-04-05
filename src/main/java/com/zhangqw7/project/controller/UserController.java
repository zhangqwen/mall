package com.zhangqw7.project.controller;

import com.alibaba.fastjson.JSONObject;
import com.zhangqw7.project.constant.RespCode;
import com.zhangqw7.project.model.Order;
import com.zhangqw7.project.model.User;
import com.zhangqw7.project.service.OrderService;
import com.zhangqw7.project.service.ProductService;
import com.zhangqw7.project.service.UserService;
import com.zhangqw7.project.utils.ResponseJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
//@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    /**
     * 注册并写入数据库
     * @param us 注册信息
     * @return 返回信息
     */
    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    @ResponseBody
    public String register(@RequestBody User us) {
        User user = userService.findUserByName(us.getUsername());
//        System.out.println(user.getUsername());
        if(user == null){
            userService.register(us);
            System.out.println(us.getUsername()+"注册成功");

            ResponseJson  rm = new ResponseJson(RespCode.CODE_1000, "注册成功");
            return JSONObject.toJSONString(rm.toMap());
        }else {
            System.out.println("用户名已存在，请更改用户名。。。");
//            return "registerForm";
            ResponseJson  rm = new ResponseJson(RespCode.CODE_1001, "用户已存在");
            return JSONObject.toJSONString(rm.toMap());
        }
    }

    /**
     * 查找所有用户
     * @return 用户列表
     */
    @RequestMapping(value = "/user/findAll")
    @ResponseBody
    public String findAll() {
        List<User> users = userService.findAllUser();
        ResponseJson  rm = new ResponseJson(RespCode.CODE_1000, "查找所有用户成功", users);
        return JSONObject.toJSONString(rm.toMap());
    }

    /**
     *登录
     * @param us 登录所需信息
     * @return 登录结果
     */
    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    @ResponseBody
//    public String login(@RequestBody User us, HttpServletRequest request) {
    public String login(@RequestBody User us) {

        User user = userService.findUserByName(us.getUsername());

//        System.out.println("系统路径为"+request.getContextPath());
//        System.out.println("Servlet路径为"+request.getServletPath());

        System.out.println(us);
        // UUID.randomUUID()

//        JSONObject object = new JSONObject(); // for test

        if(user != null) {
            if(user.getPassword().equals(us.getPassword())) {
                System.out.println("登录成功");

//                HttpSession session = request.getSession(true);

                User user_lgd = userService.findUserByName(us.getUsername());

//                Integer user_id = user_lgd.getId();
//                session.setAttribute("session", user_id);
//                return "catalogue";

                ResponseJson  rm = new ResponseJson(RespCode.CODE_1000, "登录成功", user_lgd);
                return JSONObject.toJSONString(rm.toMap());
            }else {
                System.out.println("密码错误，请重新登录");
//                return "../../index";
                ResponseJson  rm = new ResponseJson(RespCode.CODE_1001, "密码错误");
                return JSONObject.toJSONString(rm.toMap());
            }
        }else {
            System.out.println("用户不存在，请注册。。。");
//            return "registerForm";
            ResponseJson  rm = new ResponseJson(RespCode.CODE_1001, "请登录后再选购");
            return JSONObject.toJSONString(rm.toMap());
        }
    }

    /**
     * 注销用户
     * param: 用户信息
     * @return 注销结果
     */
    @RequestMapping(value = "/user/cancel", method = RequestMethod.POST)
    @ResponseBody
//    public String cancel(@RequestBody User us, HttpServletRequest request) {
    public String cancel(@RequestBody User us) {
//        HttpSession session = request.getSession();
//        Integer user_id = (Integer) session.getAttribute("session");
//        System.out.println(us);
        User user = userService.findUserById(us.getId());
        String name = user.getUsername();

        userService.cancel(user);
//        session.removeAttribute("session");
        System.out.println("控制器注销用户");

//        return "../../index";
        ResponseJson  rm = new ResponseJson(RespCode.CODE_1000, "用户"+name+"已注销", us.getId());
        return JSONObject.toJSONString(rm.toMap());
    }


    /**
     * 重置用户密码
     * @param us 账号信息
     * @return 重置结果
     */
    @RequestMapping("/user/reset")
    @ResponseBody
    public String reset(@RequestBody User us) {
        try{
            userService.update(us);
            System.out.println("控制器重置用户密码");

            ResponseJson  rm = new ResponseJson(RespCode.CODE_1000, "用户"+us.getUsername()+
                    "密码已重置为默认密码5tgb^YHN，请提醒该用户及时更改", us.getId());
            return JSONObject.toJSONString(rm.toMap());
        }catch (Exception e){
            ResponseJson  rm = new ResponseJson(RespCode.CODE_1001, "用户"+us.getId()+"密码重置失败");
            return JSONObject.toJSONString(rm.toMap());
        }

    }

    /**
     * 更新用户密码
     * @param:  用户信息
     * @return 更新结果
     */
    @RequestMapping("/user/update")
    @ResponseBody
    public String update(@RequestBody User us) {
        User user = userService.findUserById(us.getId());
        user.setPassword(us.getPassword());
        userService.update(user);
        System.out.println("控制层修改密码成功");
//        return "catalogue";
        ResponseJson  rm = new ResponseJson(RespCode.CODE_1000, "更新成功");
        return JSONObject.toJSONString(rm.toMap());
    }

    /**
     * 获取用户个人所有订单
     * @param： 用户信息
     * @return： 订单列表
     */
    @RequestMapping("/user/orders")
    @ResponseBody
//    public String viewOrders(Model model, HttpServletRequest request) {
    public String viewOrders(@RequestBody User us) {
//        HttpSession session = request.getSession(true);
//        Integer user_id = (Integer) session.getAttribute("session");
//        List<Order> orders = orderService.findOrderByUserId(user_id);

        List<Order> orders = orderService.findOrderByUserId(us.getId());

//        model.addAttribute("orders", orders);
//        model.addAttribute("productService", productService);
//        return "userOrders";

        ResponseJson  rm = new ResponseJson(RespCode.CODE_1000, "注销成功", orders);
        return JSONObject.toJSONString(rm.toMap());
    }

/*

    @RequestMapping("/user/registering")
    public String registering() {
        return "registerForm";
    }

    @RequestMapping("/user/index")
    public String main() {
        return "../../index";
    }

    @RequestMapping("/user/setting")
    public String setting() {
        return "userSetting";
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

    @RequestMapping(value = "/user/test", method=RequestMethod.POST)
    @ResponseBody
//    public JSONObject test(String username, String password) {
    public Object test(@RequestBody User user) {
//    public String test(@RequestBody String string) {
        System.out.println(user.getUsername());

//        return "../../index";
//        return JSON.parseObject(string);
        return JSONObject.toJSON(user);
    }*/
}
