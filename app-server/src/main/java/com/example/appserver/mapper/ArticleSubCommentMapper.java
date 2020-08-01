package com.example.appserver.mapper;

import com.example.appserver.model.entity.ArticleSubComment;

public interface ArticleSubCommentMapper {
    int deleteByPrimaryKey(Integer subCommentId);

    int insert(ArticleSubComment record);

    int insertSelective(ArticleSubComment record);

    ArticleSubComment selectByPrimaryKey(Integer subCommentId);

    int updateByPrimaryKeySelective(ArticleSubComment record);

    int updateByPrimaryKey(ArticleSubComment record);
}