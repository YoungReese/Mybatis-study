import com.ly.dao.TeacherMapper;
import com.ly.pojo.Teacher;
import com.ly.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;



/**
 * liyang 2020-10-13
 * 测试一对多情况
 *
 * 使用复杂映射RResultMap
 *
 */
public class MyTest {

    @Test
    public void testGetTeachers() {
        try (SqlSession sqlSession = MybatisUtils.getSqlSession()) {
            TeacherMapper mapper = sqlSession.getMapper(TeacherMapper.class);
            List<Teacher> teacherList = mapper.getTeachers();
            System.out.println(teacherList);
        }
    }

    @Test
    public void testGetTeacherById() {
        try (SqlSession sqlSession = MybatisUtils.getSqlSession()) {
            TeacherMapper mapper = sqlSession.getMapper(TeacherMapper.class);
            Teacher teacher = mapper.getTeacherById(1);
            System.out.println(teacher);
        }
    }

    @Test
    public void testGetTeacherById02() {
        try (SqlSession sqlSession = MybatisUtils.getSqlSession()) {
            TeacherMapper mapper = sqlSession.getMapper(TeacherMapper.class);
            Teacher teacher = mapper.getTeacherById02(1);
            System.out.println(teacher);
        }
    }
}
