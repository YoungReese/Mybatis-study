package com.ly.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private int id;
    private String name;
    // 学生需要关联一个老师（通过tid与数据库中的teacher进行关联）
    private Teacher teacher;
}
