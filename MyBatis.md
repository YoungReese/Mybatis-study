# MyBatis

【2020-10.07】

环境：

*   JDK 1.8
*   Mysql 5.7 或 8.0 （这里使用8.0.21）
*   maven 3.6.3 （不要使用3.6.2）
*   idea

回顾：

*   JDBC
*   Mysql
*   Java基础
*   Maven
*   Junit

**SSM框架：配置文件，看官方文档，mybatis有中文文档**





![image-20201007205824167](MyBatis.assets/image-20201007205824167.png)

## 1 简介

### 1.1 什么是 MyBatis ？

*   MyBatis 是一款优秀的**持久层框架**。
*   它支持自定义 SQL、存储过程以及高级映射。
*   MyBatis 免除了几乎所有的 JDBC 代码以及设置参数和获取结果集的工作。
*   MyBatis 可以通过简单的 XML 或注解来配置和映射原始类型、接口和 Java POJO（Plain Old Java Objects，普通老式 Java 对象）为数据库中的记录。
*   MyBatis 本是[apache](https://baike.baidu.com/item/apache/6265)的一个开源项目[iBatis](https://baike.baidu.com/item/iBatis), 2010年这个项目由apache software foundation 迁移到了google code，并且改名为MyBatis 。
*   2013年11月迁移到Github。



如何获取Mybatis？

*   maven仓库

```xml
<!-- https://mvnrepository.com/artifact/org.mybatis/mybatis -->
<dependency>
    <groupId>org.mybatis</groupId>
    <artifactId>mybatis</artifactId>
    <version>3.5.6</version>
</dependency>
```

*   github：https://github.com/mybatis/mybatis-3/releases/tag/mybatis-3.5.6
*   中文文档：https://mybatis.org/mybatis-3/zh/index.html



### 1.2 持久层

数据持久化

*   就是将程序的数据在内存的瞬时状态转化为持久状态的过程，主要存储在数据库中。
*   数据库（jdbc），io文件持久化。



为什么需要持久化 ？

*   有一些对象，不能让他丢掉

*   内存太贵



### 1.3 持久层

Dao层、Service层、Controller层...

*   完成持久化工作的代码块
*   层界限十分明显



### 1.4 为什么需要MyBatis ？

*   帮助程序员将数据存入数据库中
*   方便，框架，自动
*   传统的JDBC代码太复杂了，简化。
*   不用MyBatis也可以，更容易上手，**技术没有高低之分**

优点：

*   简单易学：本身就很小且简单。没有任何第三方依赖，最简单安装只要两个jar文件+配置几个sql映射文件易于学习，易于使用，通过文档和源代码，可以比较完全的掌握它的设计思路和实现。
*   灵活：mybatis不会对应用程序或者数据库的现有设计强加任何影响。 sql写在xml里，便于统一管理和优化。通过sql语句可以满足操作数据库的所有需求。
*   解除sql与程序代码的耦合：通过提供DAO层，将业务逻辑和数据访问逻辑分离，使系统的设计更清晰，更易维护，更易单元测试。sql和代码的分离，提高了可维护性。
*   提供映射标签，支持对象与数据库的orm字段关系映射
*   提供对象关系映射标签，支持对象关系组建维护
*   提供xml标签，支持编写动态sql。



**最重要的一点：使用的人多！**

Spring、SpringMVC、SpringBoot



## 2 第一个Mybatis程序

思路：搭建环境 - 导入Mybatis - 编写代码 - 测试

### 2.1 搭建环境

搭建数据库

```sql
首先登陆：mysql -u root -p
输入密码：xxx
创建一个数据库： create database mybatis;
查看数据库： show databases;
使用数据库： use mybatis;
创建一个表： create table user(id int(20) not null primary key, name varchar(30) default null, pwd varchar(30) default null)engine=innodb default charset=utf8;
查看表：show tables;
查看表的列属性： show columns from user;
插入数据： insert into user(id, name, pwd) values(1, 'liyang', '123456'),(2, '张三', '123456'),(3, '李四', '123456');
查看表的内容：select * from user;
```

新建项目

1、新建一个普通的maven项目

2、删除src目录

3、导入maven依赖

*   mysql驱动

```xml
<!--mysql驱动-->
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>5.1.49</version>
</dependency>
```

*   mybatis

```xml
<!-- 模版 -->
<dependency>
  <groupId>org.mybatis</groupId>
  <artifactId>mybatis</artifactId>
  <version>x.x.x</version>
</dependency>

<!-- https://mvnrepository.com/artifact/org.mybatis/mybatis -->
<dependency>
    <groupId>org.mybatis</groupId>
    <artifactId>mybatis</artifactId>
    <version>3.5.6</version>
</dependency>
```

*   junit

```xml
<dependency>
    <groupId>junit</groupId>
    <artifactId>junit</artifactId>
    <version>4.12</version>
    <scope>test</scope>
</dependency>
```



###  2.2 创建一个模块

*   编写mybatis的核心配置文件

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<!--configuration核心配置文件-->
<configuration>
    <environments default="development">
        <environment id="development">
            <transactionManager type="JDBC"/>
            <dataSource type="POOLED">
                <property name="driver" value="com.mysql.jdbc.Driver"/>
                <property name="url" value="jdbc:mysql://localhost:3306/mybatis?useSSL=true&amp;useUnicode=true&amp;characterEncoding=UTF-8"/>
                <property name="username" value="root"/>
                <property name="password" value="12345678"/>
            </dataSource>
        </environment>
    </environments>


<!--    -->
<!--    <mappers>-->
<!--        <mapper resource="org/mybatis/example/BlogMapper.xml"/>-->
<!--    </mappers>-->

</configuration>
```



*   编写mybatis的工具类

```java
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
     */
    public static SqlSession getSqlSession() {
        return sqlSessionFactory.openSession();
    }

}
```



### 2.3 编写代码

*   实体类

```java
package com.ly.pojo;

/**
 * 对应数据库表的实体类
 */
public class User {
    private int id;
    private String name;
    private String pwd;

    public User() {
    }

    public User(int id, String name, String pwd) {
        this.id = id;
        this.name = name;
        this.pwd = pwd;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pwd='" + pwd + '\'' +
                '}';
    }
}
```



*   Dao接口

```java
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
```



*   DaoImpl实现类【**这是使用jdbc的传统做法，我们这里使用MyBatis就不用创建这个实现类，通过xml文件来配置**】

```java
package com.ly.dao;

import com.ly.pojo.User;

import java.util.List;

/**
 * jdbc的处理方式
 */
public class UserDaoImpl implements UserDao {
    public List<User> getUserList() {
        return null;
    }
}
```

**使用MyBatis通过xml文件来配置替代DaoImpl**

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<!--namespace=绑定一个对应的Dao/Mapper接口-->
<mapper namespace="com.ly.dao.UserMapper">

    <!--查询语句-->
    <select id="getUserList" resultType="com.ly.pojo.User">
        select * from mybatis.user
    </select>
</mapper>
```



### 2.4 测试

注意点：

org.apache.ibatis.binding.BindingException: Type interface com.ly.dao.UserMapper is not known to the MapperRegistry.

**MapperRegistry是什么？**







org.apache.ibatis.binding.BindingException: Type interface com.ly.dao.UserMapper is not known to the MapperRegistry.









The error may exist in com/ly/dao/UserMapper.xml

Cause: org.apache.ibatis.builder.BuilderException: Error parsing SQL Mapper Configuration. Cause: java.io.IOException: Could not find resource com/ly/dao/UserMapper.xml

Caused by: java.io.IOException: Could not find resource com/ly/dao/UserMapper.xml



```xml
<!--在build中配置resource，来放置我们资源导出失败问题-->
<build>
    <resources>
        <resource>
            <directory>src/main/java</directory>
            <includes>
                <include>**/*.properties</include>
                <include>**/*.xml</include>
            </includes>
            <filtering>false</filtering>
        </resource>
        <resource>
            <directory>src/main/resources</directory>
            <includes>
                <include>**/*.properties</include>
                <include>**/*.xml</include>
            </includes>
            <filtering>false</filtering>
        </resource>
    </resources>
</build>
```

能出现问题说明：Maven静态资源过滤问题

```xml
<resources>
   <resource>
       <directory>src/main/java</directory>
       <includes>
           <include>**/*.properties</include>
           <include>**/*.xml</include>
       </includes>
       <filtering>false</filtering>
   </resource>
   <resource>
       <directory>src/main/resources</directory>
       <includes>
           <include>**/*.properties</include>
           <include>**/*.xml</include>
       </includes>
       <filtering>false</filtering>
   </resource>
</resources>
```



java.lang.NoClassDefFoundError: com/ly/pojo/user (wrong name: com/ly/pojo/User)





org.apache.ibatis.exceptions.PersistenceException: 

Error querying database.  Cause: com.mysql.jdbc.exceptions.jdbc4.CommunicationsException: Communications link failure

```xml
useSSL=true   写true 会报错，我改成false 就可以了。

2019-11-21 15:2542回复

大菠萝很优秀MySQL在高版本需要指明是否进行SSL连接
SSL协议提供服务主要： 		
       1）认证用户服务器，确保数据发送到正确的服务器； 　　 .
       2）加密数据，防止数据传输途中被窃取使用；
       3）维护数据完整性，验证数据在传输过程中是否丢失；
2020-05-28 23:336回复

大菠萝很优秀回复 @大菠萝很优秀 :Establishing SSL connection without server's identity verification is not recommended. According to MySQL 5.5.45+, 5.6.26+ and 5.7.6+ requirements SSL connection must be established by default if explicit option isn't set. For compliance with existing applications not using SSL the verifyServerCertificate property is set to 'false'. You need either to explicitly disable SSL by setting useSSL=false, or set useSSL=true and provide truststore for server certificate verification

不建议在没有服务器身份验证的情况下建立SSL连接。根据MySQL 5.5.45+、5.6.26+和5.7.6+的要求，如果不设置显式选项，则必须建立默认的SSL连接。您需要通过设置useSSL=false显式地禁用SSL，或者设置useSSL=true并为服务器证书验证提供信任存储

解决方法：
2020-05-28 23:384回复

大菠萝很优秀回复 @大菠萝很优秀 :JDBC在与数据库连接时，JDBC与MySQL版本不兼容，MySQL的版本高一些
2020-05-28 23:353回复
```





*   junit测试

测试的问题搞定了！

一个是mapper的配置问题！

一个是pom.xml配置的过滤问题！

```java
package com.ly.dao;

import com.ly.pojo.User;
import com.ly.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

/**
 * liyang 2020-10-08
 * 测试封装好的MybatisUtils生成sqlSession，然后进行数据库操作
 */
public class UserDaoTest {

    @Test
    public void test() {
        // 获得SqlSession对象
        SqlSession sqlSession = MybatisUtils.getSqlSession();

        // 方法1：getMapper然后执行sql，官方推荐
        UserDao mapper = sqlSession.getMapper(UserDao.class);
        List<User> userList = mapper.getUserList();

//        // 方法2：已经过时，不推荐
//        List<User> userList = sqlSession.selectList("com.ly.dao.UserMapper.getUserList");
        
        for (User user : userList) {
            System.out.println(user);
        }

        // 关闭SqlSession
        sqlSession.close();
    }
}
```



鉴于需要确保每次sqlSession的close操作都被执行

![image-20201008231207073](MyBatis.assets/image-20201008231207073.png)



于是改造代码如下所示：

```java
public class UserDaoTest {

    @Test
    public void test() {
        // 获得SqlSession对象
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        try {
            UserDao mapper = sqlSession.getMapper(UserDao.class);
            List<User> userList = mapper.getUserList();
            for (User user : userList) {
                System.out.println(user);
            }
        } finally {
            sqlSession.close();
        }
    }
}
```



## 3 CRUD

将UserDao及其相关配置名改为UserMapper

<img src="MyBatis.assets/image-20201009100527526.png" alt="image-20201009100527526" style="zoom:67%;" />

### 3.1 namespace

namespace中的包名要和Dao/Mapper接口的包名一致



resouce：路径是分割是'/'

其他位置的限定：全链路名，分割是'.'



只有select会自动提交，insert、update、delete并不会自动提交，所以需要手动提交，后面的操作关注三个箭头指向的类即可。





### 3.2 getUserById

```java
// 查询一个用户
User getUserById(int id);
```

```xml
<select id="getUserById" parameterType="int" resultType="com.ly.pojo.User">
    select * from mybatis.user where id = #{id};
</select>
```

```java
@Test
public void testGetUserById() {
    // 获得SqlSession对象
    SqlSession sqlSession = MybatisUtils.getSqlSession();
    try {
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.getUserById(1);
        System.out.println(user);
    } finally {
        sqlSession.close();
    }
}
```





### 3.3 addUser

```java
// 查询一个用户
User getUserById(int id);
```

```xml
<insert id="addUser" parameterType="com.ly.pojo.User">
    insert into mybatis.user (id, name, pwd) values (#{id}, #{name}, #{pwd});
</insert>
```

```java
@Test
public void testAddUser() {
    // 获得SqlSession对象
    SqlSession sqlSession = MybatisUtils.getSqlSession();
    try {
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        int res = mapper.addUser(new User(4, "王五", "12345678"));

        if (res > 0) {
            System.out.println("插入成功");
            sqlSession.commit();
        }
        System.out.println(res);
    } finally {
        sqlSession.close();
    }
}
```



### 3.4 updateUser

```java
// 修改一个用户
int updateUser(User user);
```

```xml
<update id="updateUser" parameterType="com.ly.pojo.User">
    update mybatis.user set name = #{name}, pwd = #{pwd} where id = #{id};
</update>
```

```java
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
        }
        System.out.println(res);
    } finally {
        sqlSession.close();
    }
}
```



### 3.5 deleteUser

```java
// 删除一个用户
int deleteUser(int id);
```

```xml
<delete id="deleteUser" parameterType="int">
    delete from mybatis.user where id = #{id};
</delete>
```

```java
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
        }
        System.out.println(res); // 成功返回1
    } finally {
        sqlSession.close();
    }
}
```



[带有catch异常的mybatis的crud](https://blog.csdn.net/qq_44679744/article/details/101052428)

**【以上，crud操作可能会报异常，所以最好catch一下异常，后续添加】**



### 3.6 Mybatis和JDBC的区别

[JDBC数据增删改过程](https://blog.csdn.net/u010176014/article/details/52028854?utm_medium=distribute.pc_relevant_t0.none-task-blog-BlogCommendFromMachineLearnPai2-1.channel_param&depth_1-utm_source=distribute.pc_relevant_t0.none-task-blog-BlogCommendFromMachineLearnPai2-1.channel_param)

JDBC是Java提供的一个**操作数据库的API**； MyBatis是一个持久层ORM框架，**底层是对JDBC的封装**。
MyBatis对JDBC操作数据库做了一系列的优化：
（1） mybatis使用已有的连接池管理，避免浪费资源，提高程序可靠性。
（2） mybatis提供插件自动生成DAO层代码，提高编码效率和准确性。
（3） mybatis 提供了一级和二级缓存，提高了程序性能。
（4） mybatis使用动态SQL语句，提高了SQL维护。（此优势是基于XML配置）
（5） mybatis对数据库操作结果进行自动映射



```java
 catch (Exception e) {
            System.out.println("addUser插入失败");
            e.printStackTrace();
        }
```





```java
java.lang.ExceptionInInitializerError
### Error building SqlSession.
### The error may exist in com/lhj/mapper/PersonMapper.xml
### Cause: org.apache.ibatis.builder.BuilderException: ......
```

这个时候虽然在pom.xml文件中配置了，但需要重新刷新一下（**generate sources and update folders for all projects**）

<img src="MyBatis.assets/image-20201009214143542.png" alt="image-20201009214143542" style="zoom:67%;" />



[Java DAO 模式](https://www.runoob.com/note/27029)



&serverTimezone=UTC

Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLNonTransientConnectionException: Public Key Retrieval is not allowed