import com.ly.dao.UserMapper;
import com.ly.pojo.User;
import com.ly.utils.MybatisUtils;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * liyang 2020-10-11
 * 测试ResultMap，解决数据库查询字段与实体类属性字段不同名问题
 *
 * User中pwd改成password之后，使用ResultMap
 */
public class TestUserMapper {

    static final Logger logger = Logger.getLogger(TestUserMapper.class);

    @Test
    public void testGetUserLike() {
        // 获得SqlSession对象
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            List<User> userList = mapper.getUserLike("%li%");
            for (User user : userList) {
                System.out.println(user);
            }
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testAddUserByMap() {
        // 这种使用map的插入方式还可以改造，然后用于更新操作
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);

            Map<String, Object> map = new HashMap<String, Object>();
            map.put("userId", "6");
            map.put("userName", "钱七");
            map.put("password", "123456");

            int res = mapper.addUserByMap(map);
            if (res > 0) {
                System.out.println("插入成功");
                sqlSession.commit();
            }
        } catch (Exception e) {
            System.out.println("插入失败，插入的id(主键)可能已经存在！");
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testGetUserList() {
        try (SqlSession sqlSession = MybatisUtils.getSqlSession()) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            List<User> userList = mapper.getUserList();
            for (User user : userList) {
                System.out.println(user);
            }
        }
    }

    @Test
    public void testGetUserById() {
        // 获得SqlSession对象
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            User user = mapper.getUserById(1);
            logger.info("进入了testGetUserById函数！");
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
            int res = mapper.addUser(new User(9, "孙八", "12345678"));
            if (res > 0) {
                System.out.println("插入成功");
                sqlSession.commit();
            }
        } catch (Exception e) {
            System.out.println("插入失败，插入的id(主键)可能已经存在！");
            // e.printStackTrace();
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
            int res = mapper.deleteUser(7);
            if (res > 0) {
                System.out.println("删除成功");
                sqlSession.commit();
            } else {
                System.out.println("删除失败");
            }
        } finally {
            sqlSession.close();
        }
    }


    @Test
    public void testLog4j() {
        logger.info("info:进入了testLog4j");
        logger.debug("debug:进入了testLog4j");
        logger.error("error:进入了testLog4j");
    }

    @Test
    public void testGetUserByLimit() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);

            Map<String, Integer> map = new HashMap<>();
            map.put("startIndex", 2);
            map.put("pageSize", 2);

            List<User> userList = mapper.getUserByLimit(map);
            for (User user : userList) {
                System.out.println(user);
            }
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testGetUserByRowBounds() {
        try (SqlSession sqlSession = MybatisUtils.getSqlSession();) {
            //RowBounds实现
            RowBounds rowBounds = new RowBounds(1, 2);
            //通过Java代码层面实现分页，使用全限定名
            List<User> userList = sqlSession.selectList("com.ly.dao.UserMapper.getUserByRowBounds",null, rowBounds);
            for (User user: userList) {
                System.out.println(user);
            }
        }
    }
}


