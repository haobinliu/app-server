package com.example.appserver.model.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author liubinhao
 */
@Data
public class ArticleClassify {

    private Integer classifyId;

    private String  classifyName;

    private Date    createTime;

    private Date    updateTime;

    private Integer modifyUserId;

}