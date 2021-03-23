package com.ly.dao;

import com.ly.pojo.User;
import com.ly.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * liyang 2020-10-08
 * 测试封装好的MybatisUtils生成sqlSession，然后进行数据库操作
 */
public class UserMapperTest {

    @Test
    public void testGetUserList() {
        // 获得SqlSession对象
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            List<User> userList = mapper.getUserList();
            for (User user : userList) {
                System.out.println(user);
            }
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testGetUserById() {
        // 获得SqlSession对象
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            User user = mapper.getUserById(11);
            System.out.println(user);
        } finally {
            sqlSession.close();
        }
    }

    // 增删改需要提交事物
    @Test
    public void testAddUser() {
        // 获得SqlSession对象
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            User user = new User();
            user.setId(11);
            user.setName("none");
            int res = mapper.addUser(user);
            if (res > 0) {
                System.out.println("插入成功");
                sqlSession.commit();
            }
            System.out.println(res);
        } catch (Exception e) {
            System.out.println("插入失败，插入的id(主键)可能已经存在！");
//            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testUpdateUser() {
        // 获得SqlSession对象
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            int res = mapper.updateUser(new User(4, "Alice", "123456"));
            if (res > 0) {
                System.out.println("更新成功");
                sqlSession.commit();
            } else {
                System.out.println("更新失败");
            }
            System.out.println(res);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testDeleteUser() {
        // 获得SqlSession对象
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            int res = mapper.deleteUser(4);
            if (res > 0) {
                System.out.println("删除成功");
                sqlSession.commit();
            } else {
                System.out.println("删除失败");
            }
            System.out.println(res); // 成功返回1
        } finally {
            sqlSession.close();
        }
    }

}
