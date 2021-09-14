package com.leon.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @PROJECT_NAME: hello-tools
 * @CLASS_NAME: Student
 * @AUTHOR: OceanLeonAI
 * @CREATED_DATE: 2021/9/13 17:59
 * @Version 1.0
 * @DESCRIPTION: 学生信息
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Student implements Serializable {

    /**
     * 学号
     */
    private Long id;

    /**
     * 班级信息
     */
    private String classInfo;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别
     */
    private int sex;

    /**
     * 年龄
     */
    private int age;

}
