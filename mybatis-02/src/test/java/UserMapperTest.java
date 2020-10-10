import com.ly.dao.UserMapper;
import com.ly.pojo.User;
import com.ly.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * liyang 2020-10-10
 * 测试封装好的MybatisUtils生成sqlSession，然后进行数据库操作
 */
public class UserMapperTest {

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
            map.put("userId", "5");
            map.put("userName", "赵六");
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
        // 获得SqlSession对象
        // Try-with-resources are not supported at language level '5’错误。
        // 在pom.xml的build标签加了使用jdk1.8的配置
        try (SqlSession sqlSession = MybatisUtils.getSqlSession()) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            List<User> userList = mapper.getUserList();
            for (User user : userList) {
                System.out.println(user);
            }
            // First: The try-with-resources statement provides an automatic close at the end of its scope.
            //Second: Flush is redundant before close.
            sqlSession.close(); // Redundant 'close': close is not request, the reason is explained above
        }
    }

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

    // 增删改需要提交事物
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
