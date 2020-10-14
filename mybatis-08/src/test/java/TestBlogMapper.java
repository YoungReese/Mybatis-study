import com.ly.dao.BlogMapper;
import com.ly.pojo.Blog;
import com.ly.utils.IdUtils;
import com.ly.utils.MybatisUtils;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            int res = mapper.deleteBlogByTitle("jdbc");
            if (res > 0) System.out.println("删除成功！");
            else System.out.println("删除失败！");
        }
    }


    // 条件查询: List<Blog> queryBlogsIF(Map<String, String> map);
    @Test
    public void testQueryBlogsIF() {
        Map<String, String> map = new HashMap<>();
        map.put("title", "spring%");
        map.put("author", "liyang");

        try(SqlSession sqlSession = MybatisUtils.getSqlSession()) {
            BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
            List<Blog> blogList = mapper.queryBlogsIF(map);
            for (Blog blog : blogList) {
                System.out.println(blog);
            }
        }
    }


    // 条件查询: List<Blog> queryBlogsChoose(Map<String, String> map);
    @Test
    public void testQueryBlogsChoose() {
        Map<String, String> map = new HashMap<>();
        map.put("title", "spring%");
        map.put("author", "liyang");

        try(SqlSession sqlSession = MybatisUtils.getSqlSession()) {
            BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
            List<Blog> blogList = mapper.queryBlogsChoose(map);
            for (Blog blog : blogList) {
                System.out.println(blog);
            }
        }
    }

    // 更新：int updateBlogById(Map map);
    @Test
    public void testUpdateBlogById() {
        Map<String, String> map = new HashMap<>();
        map.put("title", "spring5");
        map.put("views", "999");
        map.put("id", "1");
        try(SqlSession sqlSession = MybatisUtils.getSqlSession()) {
            BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
            int res = mapper.updateBlogById(map);
            if (res > 0) System.out.println("更新成功！");
        }
    }



}
