package com.ly.dao;

import com.ly.pojo.User;

import java.util.List;

/**
 * UserDao等价于Mybatis中的Mapper
 * 以后的操作中都使用Mapper
 */
public interface UserDao {
    List<User> getUserList();

}
