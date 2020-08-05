package com.example.appserver.service.impl;

import com.example.appserver.mapper.ArticleCommentMapper;
import com.example.appserver.mapper.ArticleSubCommentMapper;
import com.example.appserver.model.entity.ArticleComment;
import com.example.appserver.model.entity.ArticleSubComment;
import com.example.appserver.model.req.DropCommentReq;
import com.example.appserver.service.ArticleCommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author liubinhao
 * @date 2020/8/2
 */
@Service
public class ArticleCommentServiceImpl implements ArticleCommentService {

    private static final byte IS_VALID_FAIL = 0;

    private static final Integer ARTICLE_LEVEL_MAIN = 1;

    private static final Integer ARTICLE_LEVEL_SUB = 2;

    @Resource
    private ArticleCommentMapper articleCommentMapper;

    @Resource
    private ArticleSubCommentMapper articleSubCommentMapper;

    @Override
    public ArticleComment publishComment(ArticleComment comment) {
        try {
            articleCommentMapper.insert(comment);
        }catch (Exception e){
            e.printStackTrace();
        }
        return comment;
    }

    @Override
    public boolean dropComment(DropCommentReq req) {
        boolean result = true;
        Integer commentLevel = req.getCommentLevel();
        Integer commentId = req.getCommentId();
        try {
            if (commentLevel.equals(ARTICLE_LEVEL_MAIN)) {
                ArticleComment articleComment = articleCommentMapper.selectByPrimaryKey(commentId);
                articleComment.setIsValid(IS_VALID_FAIL);
                articleCommentMapper.updateByPrimaryKey(articleComment);
            } else if (commentId.equals(ARTICLE_LEVEL_SUB)) {
                ArticleSubComment articleSubComment = articleSubCommentMapper.selectByPrimaryKey(commentId);
                articleSubComment.setIsValid(IS_VALID_FAIL);
                articleSubCommentMapper.updateByPrimaryKey(articleSubComment);
            }
        }catch (Exception e){
            e.printStackTrace();
            result = false;
        }
        return result;
    }

    @Override
    public boolean favorComment(DropCommentReq req) {
        return false;
    }
}
