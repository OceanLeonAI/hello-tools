package com.leon.util;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.config.Configure;
import com.deepoove.poi.data.PictureRenderData;
import com.deepoove.poi.policy.HackLoopTableRenderPolicy;
import com.leon.entity.StudentScore;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * word 导出工具类
 */
@Slf4j
public class WordExportUtils {

    /**
     * 测试
     * 注意 springboot 打包把 main 方法注释掉
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        exportStudentScoreReportTest();
    }

    /**
     * 下载 word 文档
     *
     * @param renderData
     * @param request
     * @param response
     * @throws IOException
     */
    public static void exportStudentScoreReport(Map<String, Object> renderData, HttpServletRequest request, HttpServletResponse response) throws IOException {

        // 插入图片
        renderData.put("ai", new PictureRenderData(
                250, 260,
                WordExportUtils.class.getClassLoader().getResource("").getPath() + "picture/ai.jpg")
        );

        log.info("待渲染数据 ---> {}", renderData);

        //模版路径
        String templatePath = "/word/" + "word_template_student_score" + ".docx";

        // 获取资源
        InputStream resourceAsStream = WordExportUtils.class.getResourceAsStream(templatePath);

        //导出到指定路径
        XWPFTemplate template = null;
        FileOutputStream out = null;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String formatDateString = sdf.format(new Date());

        String studentName = (String) ((Map<String, Object>) renderData.get("student")).get("name");
        String fileName = "学生成绩报告_" + studentName + "_" + formatDateString;

        String fileNameNew = new String(fileName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);

        // 输出流
        ServletOutputStream outputStream;

        try {
            //渲染表格
            HackLoopTableRenderPolicy policy = new HackLoopTableRenderPolicy();
            Configure config = Configure.newBuilder()
                    .bind("scoreList", policy) // 学生成绩列表
                    .build();
            // 输出 word 内容文件流，提供下载
            // template = XWPFTemplate.compile(resource.getInputStream()).render(map);
            template = XWPFTemplate.compile(resourceAsStream).render(renderData); // 渲染数据

            response.reset();
            response.setContentType("application/x-msdownload");
            response.setHeader("Content-Disposition",
                    "attachment; filename=" + fileNameNew + ".doc");
            outputStream = response.getOutputStream();
            template.write(outputStream);
            outputStream.flush(); // 刷新流
            outputStream.close(); // 关闭流

        } catch (IOException e) {
            throw e;
        }
    }


    /**
     * 本地导出 word 文档
     *
     * @throws Exception
     */
    private static void exportStudentScoreReportTest() throws Exception {

        // 获取模拟数据
        Map<String, Object> studentScoreMockDataMap = StudentScore.getStudentScoreMockDataMap();

        // 插入图片
        studentScoreMockDataMap.put("ai", new PictureRenderData(
                250, 260,
                WordExportUtils.class.getClassLoader().getResource("").getPath() + "picture/ai.jpg")
        );

        // 获取模板路径
        String templatePath = WordExportUtils.class.getClassLoader().getResource("").getPath() + "/word/word_template_student_score.docx";

        // 获取资源
        FileInputStream fis = new FileInputStream(templatePath);

        //导出模版路径
        // String wordExportPath = "F:\\word";
        String wordExportPath = WordExportUtils.class.getClassLoader().getResource("").getPath() + "/export/";

        String uuid = UUID.randomUUID().toString();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String format = sdf.format(new Date());
        String fileName = File.separator + format + "---" + uuid + ".docx";
        String wordExportFilePath = wordExportPath + fileName;

        File file = new File(wordExportPath);
        if (!file.exists()) {
            file.mkdirs();
        }

        //导出到指定路径
        XWPFTemplate template = null;
        FileOutputStream out = null;

        try {
            //渲染表格
            HackLoopTableRenderPolicy policy = new HackLoopTableRenderPolicy();
            Configure config = Configure.newBuilder()
                    .bind("scoreList", policy) // 绑定表格数据
                    .build();

            template = XWPFTemplate.compile(fis, config).render(studentScoreMockDataMap); // 渲染数据
            out = new FileOutputStream(wordExportFilePath);
            template.write(out);
            out.flush();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw e;
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (template != null) {
                    template.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
