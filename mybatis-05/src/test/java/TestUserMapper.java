import com.ly.dao.UserMapper;
import com.ly.pojo.User;
import com.ly.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

/**
 * liyang 2020-10-11
 * 使用注解来映射简单语句会使代码显得更加简洁，
 * 但对于稍微复杂一点的语句，Java 注解不仅力不从心，
 * 还会让你本就复杂的 SQL 语句更加混乱不堪。
 * 因此，如果你需要做一些很复杂的操作，最好用 XML 来映射语句。
 *
 * 这里的注解就无法解决实体类password与数据库字段pwd的映射问题，
 * 因此这里为了方便测试注解，将实体类的password改为了我pwd
 */
public class TestUserMapper {
    @Test
    public void testGetUsers() {
        try (SqlSession sqlSession = MybatisUtils.getSqlSession()) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            //通过Java代码层面实现分页，使用全限定名
            List<User> users = mapper.getUsers();
            for (User user: users) {
                System.out.println(user);
            }
        }
    }
}
