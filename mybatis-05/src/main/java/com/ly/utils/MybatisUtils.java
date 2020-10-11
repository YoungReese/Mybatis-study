package com.ly.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;


/**
 * liyang 2020-10-08
 *
 * 工具类：建造SqlSessionFactory对象，从工厂中获取SqlSession
 *
 * update：2020-10-11 增加事物自动提交
 */
public class MybatisUtils {

    private static SqlSessionFactory sqlSessionFactory;

    static {
        String resource = "mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    /**
     * 既然有了 SqlSessionFactory，顾名思义，我们可以从中获得 SqlSession 的实例。
     * SqlSession 提供了在数据库执行 SQL 命令所需的所有方法。
     * 你可以通过 SqlSession 实例来直接执行已映射的 SQL 语句。
     *
     * SqlSession openSession(); // 默认自动提交为false
     * SqlSession openSession(boolean autoCommit); // 设置自动提交为true
     */
    public static SqlSession getSqlSession() {
        return sqlSessionFactory.openSession(true);
    }

}
