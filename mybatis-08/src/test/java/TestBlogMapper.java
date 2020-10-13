import com.ly.dao.BlogMapper;
import com.ly.pojo.Blog;
import com.ly.utils.IdUtils;
import com.ly.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.Date;
import java.util.List;

public class TestBlogMapper {

    @Test
    public void testAddBlog() {
        try (SqlSession sqlSession = MybatisUtils.getSqlSession()) {
            BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
            int res = mapper.addBlog(new Blog(IdUtils.getId(), "jdbc", "liyang", new Date(), 100));
            if (res > 0) System.out.println("插入成功！");
        }
    }

    @Test
    public void testGetBlogs() {
        try (SqlSession sqlSession = MybatisUtils.getSqlSession()) {
            BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
            List<Blog> blogList = mapper.getBlogs();
            for (Blog blog : blogList) {
                System.out.println(blog);
            }
        }
    }

    @Test
    public void testDeleteBlogByTitle() {
        try (SqlSession sqlSession = MybatisUtils.getSqlSession()) {
            BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
            int res = mapper.deleteBlogByTitle("jdbc-blog");
            if (res > 0) System.out.println("删除成功！");
        }
    }

}
