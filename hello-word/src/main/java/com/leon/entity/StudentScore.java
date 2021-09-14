package com.leon.entity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @PROJECT_NAME: hello-tools
 * @CLASS_NAME: StudentScore
 * @AUTHOR: OceanLeonAI
 * @CREATED_DATE: 2021/9/13 18:07
 * @Version 1.0
 * @DESCRIPTION: 学生成绩信息
 **/
@Slf4j
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentScore implements Serializable {

    private static final long serialVersionUID = -1837498664353317342L;

    /**
     * 学生信息
     */
    private Student student;

    /**
     * 成绩信息
     */
    private List<Score> scoreList;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private String createTime;


    /**
     * 生成模拟数据 实体
     *
     * @return
     */
    public static StudentScore getStudentScoreMockDataEntity() {

        Student student = Student.builder()
                .id(1L)
                .classInfo("高三1班")
                .name("leon")
                .sex(1)
                .age(18)
                .build();

        List<Score> scoreList = new ArrayList<Score>() {

            private static final long serialVersionUID = 7976556000187989819L;

            {
                add(Score.builder().subjectName("语文").score(90d).build());
                add(Score.builder().subjectName("数学").score(99.99d).build());
                add(Score.builder().subjectName("英语").score(100d).build());
            }

        };

        StudentScore studentScore = StudentScore.builder()
                .student(student)
                .scoreList(scoreList)
                .remark("这是一段学生成绩的备注信息")
                .build();

        log.info("studentScore --- {}", studentScore);

        // 转换为 map
        JSON jsons = (JSON) JSON.toJSON(studentScore);
        Map<String, Object> studentScoreMap = JSONObject.parseObject(jsons.toString());
        log.info("studentScore map --- {}", studentScoreMap);

        return studentScore;
    }

    /**
     * 生成模拟数据 Map
     *
     * @return
     */
    public static Map<String, Object> getStudentScoreMockDataMap() {

        Student student = Student.builder()
                .id(1L)
                .classInfo("高三1班")
                .name("leon")
                .sex(1)
                .age(18)
                .build();

        List<Score> scoreList = new ArrayList<Score>() {

            private static final long serialVersionUID = 7976556000187989819L;

            {
                add(Score.builder().subjectName("语文").score(90d).build());
                add(Score.builder().subjectName("数学").score(99.99d).build());
                add(Score.builder().subjectName("英语").score(100d).build());
            }

        };

        StudentScore studentScore = StudentScore.builder()
                .student(student)
                .scoreList(scoreList)
                .remark("这是一段学生成绩的备注信息")
                .createTime(new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss").format(new Date()))
                .build();

        // log.info("studentScore po --- {}", studentScore);

        // 转换为 map
        JSON jsons = (JSON) JSON.toJSON(studentScore);
        Map<String, Object> studentScoreMap = JSONObject.parseObject(jsons.toString());
        // log.info("studentScore map --- {}", studentScoreMap);

        return studentScoreMap;
    }

    /**
     * 注意 springboot 打包把 main 方法注释掉
     *
     * @param args
     */
    public static void main(String[] args) {
        getStudentScoreMockDataEntity();
    }

}
