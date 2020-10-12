import com.ly.dao.StudentMapper;
import com.ly.dao.TeacherMapper;
import com.ly.pojo.Student;
import com.ly.pojo.Teacher;
import com.ly.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class MyTest {
    @Test
    public void testGetTeacher() {
        try (SqlSession sqlSession = MybatisUtils.getSqlSession()) {
            TeacherMapper mapper = sqlSession.getMapper(TeacherMapper.class);
            Teacher teacher = mapper.getTeacher(1);
            System.out.println(teacher);
        }
    }

    @Test
    public void testGetStudents() {
        try (SqlSession sqlSession = MybatisUtils.getSqlSession()) {
            StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
            List<Student> stuList = mapper.getStudents();
            for (Student student: stuList) System.out.println(student);
        }
    }

    @Test
    public void testGetStudents02() {
        try (SqlSession sqlSession = MybatisUtils.getSqlSession()) {
            StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
            List<Student> stuList = mapper.getStudents();
            for (Student student: stuList) System.out.println(student);
        }
    }

}
