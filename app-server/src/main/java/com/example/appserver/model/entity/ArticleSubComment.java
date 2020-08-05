package com.example.appserver.model.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author liubinhao
 */
@Data
public class ArticleSubComment {

    private Integer subCommentId;

    private String  subCommentContent;

    private Integer parentCommentId;

    private Integer commentUserId;

    private String  nickName;

    private Integer commentReplyUserId;

    private String  replyUserNickName;

    private Date    createTime;

    private Byte    isValid;

    private Integer favorCount;

}