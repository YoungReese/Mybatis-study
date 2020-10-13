package com.ly.dao;

import com.ly.pojo.Teacher;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TeacherMapper {

    @Select("select * from teacher;")
    List<Teacher> getTeachers();

    Teacher getTeacherById(@Param("tid") int id);
    Teacher getTeacherById02(@Param("tid") int id);
}
