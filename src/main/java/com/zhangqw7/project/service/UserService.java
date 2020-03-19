package com.zhangqw7.project.service;

import com.zhangqw7.project.model.User;

public interface UserService {

    public void register(User user);

    public void cancel(User user);

    public void update(User user);

    public User findUserByName(String username);

    public User findUserById(Integer id);
}
