package com.ly.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

/**
 * 对应数据库表的实体类
 *
 * 更新：注释掉原先通过java生成的函数，使用lombok注解生成
 *
 * 使用方法：
 * 1、下载lombok
 * 2、导入lombok依赖
 * 3、使用注解生成相关函数
 *
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int id;
    private String name;
    private String pwd;

//    public User() {
//    }
//
//    public User(int id, String name, String pwd) {
//        this.id = id;
//        this.name = name;
//        this.pwd = pwd;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getPassword() {
//        return pwd;
//    }
//
//    public void setPassword(String pwd) {
//        this.pwd = pwd;
//    }
//
//    @Override
//    public String toString() {
//        return "User{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", pwd='" + pwd + '\'' +
//                '}';
//    }

}
