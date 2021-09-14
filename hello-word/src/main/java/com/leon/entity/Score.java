package com.leon.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @PROJECT_NAME: hello-tools
 * @CLASS_NAME: Score
 * @AUTHOR: OceanLeonAI
 * @CREATED_DATE: 2021/9/13 18:02
 * @Version 1.0
 * @DESCRIPTION: 成绩信息
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Score implements Serializable {

    private static final long serialVersionUID = 9218498835773355325L;

    /**
     * 序号
     */
    private int index;

    /**
     * 科目名称
     */
    private String subjectName;

    /**
     * 科目分数
     */
    private double score;

    /**
     * 备注
     */
    private String remark;

}
