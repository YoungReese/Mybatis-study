package com.ly.dao;

import com.ly.pojo.User;

import java.util.List;
import java.util.Map;

/**
 * UserDao等价于Mybatis中的Mapper
 * 以后的操作中都使用Mapper
 */
public interface UserMapper {
    // 查询某一类满足条件的用户
    List<User> getUserLike(String value);

    //
    int addUserByMap(Map<String, Object> map);

    // 查询全部用户
    List<User> getUserList();

    // 查询一个用户
    User getUserById(int id);

    // 增加一个用户
    int addUser(User user);

    // 修改一个用户
    int updateUser(User user);

    // 删除一个用户
    int deleteUser(int id);
}
