package com.example.appserver.mapper;

import com.example.appserver.model.entity.Article;

import java.util.List;

/**
 * @author liubinhao
 */
public interface ArticleMapper {

    int insert(Article record);

    Article selectByPrimaryKey(Integer articleId);

    int updateByPrimaryKeyWithBLOBs(Article record);

    int updateByPrimaryKey(Article record);
    /**
     * find all the articles that belongs the one whose userId is below userId
     * @param userId the person whose key is userId
     * @return all the articles belongs to the one user
     */
    List<Article> selectByUserId(Integer userId);
    /**
     * set the article to invalid
     * @param articleId pk of table article
     * @return the affected number of the row
     */
    int setIsValidByArticleId(Integer articleId);
}