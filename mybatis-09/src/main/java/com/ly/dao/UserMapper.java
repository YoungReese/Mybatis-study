package com.ly.dao;

import com.ly.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface UserMapper {

    List<User> queryUsers();

    @Select("select * from user where id = #{id};")
    User queryUserById(@Param("id") int id);

    @Update("update user set name = #{name}, pwd = #{pwd} where id = #{id}")
    int updateUserById(@Param("id") int id, @Param("name") String name, @Param("pwd") String pwd);

    int updateUserById02(User user);

}
