package com.example.appserver.service;

import com.example.appserver.model.entity.ArticleComment;
import com.example.appserver.model.req.DropCommentReq;

/**
 * @author liubinhao
 * @date 2020/8/2
 */
public interface ArticleCommentService {

    /**
     * 对文章进行评论
     * @param comment 评论内容
     * @return 评论内容
     */
    ArticleComment publishComment(ArticleComment comment);

    /**
     * 删除评论
     * @param req 请求
     * @return true 删除成功 false 删除失败
     */
    boolean dropComment(DropCommentReq req);

    /**
     * 点赞
     * @param req 请求
     * @return true 成功 false 失败
     */
    boolean favorComment(DropCommentReq req);
}
