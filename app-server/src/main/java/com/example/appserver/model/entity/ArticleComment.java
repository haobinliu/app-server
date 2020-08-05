package com.example.appserver.model.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author liubinhao
 */
@Data
public class ArticleComment {

    private Integer articleCommentId;

    private Integer articleId;

    private Integer userId;

    private String  nickName;

    private String  commentContent;

    private Date    createTime;

    private Byte    isValid;

    private Integer favorCount;
}