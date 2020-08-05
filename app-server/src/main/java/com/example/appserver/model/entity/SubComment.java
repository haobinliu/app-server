package com.example.appserver.model.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author liubinhao
 * @date 2020/7/14
 */
@Data
public class SubComment {

    private Integer subCommentId;

    private Integer commentId;

    /**
     * 被评论用户id
     */
    private Integer commentUserId;

    private String  commentContent;

    private Date    createdTime;

    private Date    updatedTime;

}
