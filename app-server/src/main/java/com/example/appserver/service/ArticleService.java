package com.example.appserver.service;

import com.example.appserver.model.entity.Article;
import com.example.appserver.model.req.ArticleSearchReq;

import java.util.List;

/**
 * @author liubinhao
 * @date 2020/8/2
 */
public interface ArticleService {

    /**
     * 查看文章
     * @param req 根据用户ID或者文章ID查询符合条件的所有文章
     * @return 符合条件的文章List集合
     */
    List<Article> exhibitArticle(ArticleSearchReq req);

    /**
     * 某用户发文章
     * @param article 文章内容
     * @return 成功返回true 错误返回false
     */
    boolean publishArticle(Article article);

    /**
     * 修改已经存在的文章
     * @param article 文章内容
     * @return 成功返回true 错误返回false
     */
    boolean editArticle(Article article);

    /**
     * 删除已经存在的文章
     * @param articleId 文章ID
     * @return 成功返回true 错误返回false
     */
    boolean dropArticle(Integer articleId);

}
