import com.ly.dao.UserMapper;
import com.ly.pojo.User;
import com.ly.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

/**
 * liyang 2020-10-14
 * cache相关测试
 */
public class MyTest {

    @Test
    public void testQueryUsers() {
        try (SqlSession sqlSession = MybatisUtils.getSqlSession()) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            List<User> userList = mapper.queryUsers();
            for (User user : userList) {
                System.out.println(user);
            }
        }
    }

    @Test
    public void testQueryUserById() {
        try (SqlSession sqlSession = MybatisUtils.getSqlSession()) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            User user1 = mapper.queryUserById(1);
            System.out.println(user1);

//            sqlSession.clearCache();

            User user2 = mapper.queryUserById(1);
            System.out.println(user2);
            System.out.println(user1 == user2);
        }
    }



    @Test
    public void testUpdateUserById() {
        try (SqlSession sqlSession = MybatisUtils.getSqlSession()) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);

            int res = mapper.updateUserById(4, "Alice", "123456");
            if (res > 0) System.out.println(res);

            res = mapper.updateUserById02(new User(4, "王五", "123456789"));
            if (res > 0) System.out.println("更新成功！");
        }
    }






}
