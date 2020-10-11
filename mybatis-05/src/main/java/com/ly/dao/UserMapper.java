package com.ly.dao;

import com.ly.pojo.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 使用注解实现它的操作
 */
public interface UserMapper {
    @Select("select * from user")
    List<User> getUsers();

}
