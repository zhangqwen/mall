package com.zhangqw7.project.service.impl;

import com.zhangqw7.project.dao.UserDao;
import com.zhangqw7.project.model.User;
import com.zhangqw7.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public void register(User user) {
        System.out.println("服务层注册用户。。。");
        userDao.register(user);
    }

    @Override
    public void cancel(User user) {
        System.out.println("服务层注销账户。。。");
        userDao.cancel(user);
    }

    @Override
    public void update(User user) {
        System.out.println("服务层更改账户信息。。。");
        userDao.update(user);
    }

    @Override
    public User findUserByName(String username) {
        System.out.println("服务层查询指定姓名用户。。。");
        return userDao.findUserByName(username);
    }

    @Override
    public User findUserById(Integer id) {
        System.out.println("服务层查询指定id用户。。。");
        return userDao.findUserById(id);
    }

    @Override
    public List<User> findAllUser() {
        return userDao.findAllUser();
    }

    @Override
    public void deleteUserById(Integer id){
        userDao.deleteUserById(id);
    }
}
