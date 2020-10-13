package com.ly.dao;

import com.ly.pojo.Blog;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface BlogMapper {
    // 增加
    int addBlog(Blog blog);

    // 查找
    @Select("select * from blog;")
    List<Blog> getBlogs();

//    // 更新
//    int updateBlog(int id);

    // 删除
    @Delete("delete from blog where title = #{title};")
    int deleteBlogByTitle(@Param("title") String title);

    // 条件查询
    List<Blog> queryBlogsIF(Map<String, String> map);

}
