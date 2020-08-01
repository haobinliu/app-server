package com.example.appserver.mapper;

import com.example.appserver.model.entity.ArticleClassify;

public interface ArticleClassifyMapper {
    int deleteByPrimaryKey(Integer classifyId);

    int insert(ArticleClassify record);

    int insertSelective(ArticleClassify record);

    ArticleClassify selectByPrimaryKey(Integer classifyId);

    int updateByPrimaryKeySelective(ArticleClassify record);

    int updateByPrimaryKey(ArticleClassify record);
}