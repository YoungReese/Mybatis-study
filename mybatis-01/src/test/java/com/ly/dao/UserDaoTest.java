package com.ly.dao;

import com.ly.pojo.User;
import com.ly.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class UserDaoTest {

    @Test
    public void test() {

        // 获得SqlSession对象
        SqlSession sqlSession = MybatisUtils.getSqlSession();

        // 方法1：getMapper然后执行sql
        UserDao mapper = sqlSession.getMapper(UserDao.class);
        List<User> userList = mapper.getUserList();
        for (User user : userList) {
            System.out.println(user);
        }

        // 关闭SqlSession
        sqlSession.close();
    }
}
