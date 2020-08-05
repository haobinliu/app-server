package com.example.appserver.model.entity;

import lombok.Data;

import java.util.Date;
/**
 * @author liubinhao
 */
@Data
public class Article {

    private Integer articleId;

    private String  title;

    private String  classifyId;

    private Integer userId;

    private Date    createTime;

    private Date    updateTime;

    private Integer viewCount;

    private Integer favorCount;

    private String  content;

    private Byte    isValid;
}