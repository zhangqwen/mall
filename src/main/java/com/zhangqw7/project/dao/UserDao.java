package com.zhangqw7.project.dao;

import com.zhangqw7.project.model.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {

    @Insert("insert into User (username, password) value(#{username}, #{password})")
    public void register(User user);

    @Delete("delete from User where username=#{username}")
    public void cancel(User user);

    @Update("update User set password=#{password} where id=#{id}")
    public void update(User user);

    @Select("select * from User where username=#{username}")
    public User findUserByName(String username);

    @Select("select * from User where id=#{id}")
    public User findUserById(Integer id);
}
