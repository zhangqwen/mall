package com.zhangqw7.project.service;

import com.zhangqw7.project.model.User;

import java.util.List;

public interface UserService {

    void register(User user);

    void cancel(User user);

    void update(User user);

    User findUserByName(String username);

    User findUserById(Integer id);

    List<User> findAllUser();

    void deleteUserById(Integer id);
}
