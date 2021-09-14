package com.leon.controller;

import com.leon.entity.StudentScore;
import com.leon.util.WordExportUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @PROJECT_NAME: hello-tools
 * @CLASS_NAME: WordDemoController
 * @AUTHOR: OceanLeonAI
 * @CREATED_DATE: 2021/9/13 16:07
 * @Version 1.0
 * @DESCRIPTION:
 **/
@RestController
@RequestMapping("/word")
public class WordDemoController {

    /**
     * 学生成绩 word 下载
     *
     * @param request
     * @param response
     */
    @GetMapping("/studentScoreDownload")
    public void studentScoreDownload(HttpServletRequest request, HttpServletResponse response) {
        try {
            // 获取模拟数据
            Map<String, Object> studentScoreMockDataMap = StudentScore.getStudentScoreMockDataMap();
            // 调用工具类
            WordExportUtils.exportStudentScoreReport(studentScoreMockDataMap, request, response);
        } catch (Exception e) {
            // TODO: 异常处理
            e.printStackTrace();
        }
    }
}
