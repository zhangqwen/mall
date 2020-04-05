package com.zhangqw7.project.dao;

import com.zhangqw7.project.model.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {

    /**
     * 新建用户
     * @param user 用户名user.username, 用户密码user.password;
     */
    @Insert("insert into User (username, password) value(#{username}, #{password})")
    void register(User user);

    /**
     * 根据用户名删除用户
     * @param user 用户名user.username
     */
    @Delete("delete from User where username=#{username}")
    void cancel(User user);

    /**
     * 根据用户ID删除用户
     * @param id 用户id
     */
    @Delete("delete from User where id=#{id}")
    void deleteUserById(Integer id);

    /**
     * 根据用户ID更新密码
     * @param user 用户id：user.id, 用户密码：user.password
     */
    @Update("update User set password='5tgb^YHN' where id=#{id}")
    void update(User user);

    /**
     * 根据用户名查找用户
     * @param username 用户名
     * @return 用户User
     */
    @Select("select * from User where username=#{username}")
    User findUserByName(String username);

    /**
     * 根据用户ID查找用户
     * @param id 用户ID
     * @return 用户User
     */
    @Select("select * from User where id=#{id}")
    User findUserById(Integer id);

    /**
     * 查找所有用户
     * @return 用户列表
     */
    @Select("select * from user where id>1")
    List<User> findAllUser();

}



// D:\Tools\mysql\mysql-8.0.19-winx64\bin