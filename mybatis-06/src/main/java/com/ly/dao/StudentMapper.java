package com.ly.dao;

import com.ly.pojo.Student;

import java.util.List;

public interface StudentMapper {
    // 查询出所有学生的信息，以及对应的老师的信息
    // select student.id, student.name, teacher.name from student, teacher where student.tid = teacher.id;
    List<Student> getStudents();
    List<Student> getStudents02();
}
