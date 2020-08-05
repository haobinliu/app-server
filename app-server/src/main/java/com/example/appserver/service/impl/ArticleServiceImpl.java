package com.example.appserver.service.impl;

import com.example.appserver.mapper.ArticleMapper;
import com.example.appserver.model.entity.Article;
import com.example.appserver.model.req.ArticleSearchReq;
import com.example.appserver.service.ArticleService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author liubinhao
 * @date 2020/8/2
 */

@Service
public class ArticleServiceImpl implements ArticleService {

    @Resource
    private ArticleMapper articleMapper;

    @Override
    public List<Article> exhibitArticle(ArticleSearchReq req) {
        //保留，暂时不可通过分类查询
        List<Article> result;
        Integer userId    = req.getUserId();
        Integer articleId = req.getArticleId();
        if (userId!=null) {
            result = articleMapper.selectByUserId(userId);
        }else if (articleId!=null){
            Article article = articleMapper.selectByPrimaryKey(articleId);
            if (article == null){
                return null;
            }
            result = Collections.singletonList(article);
        }else {
            return null;
        }
        return result;
    }

    @Override
    public boolean publishArticle(Article article) {
        boolean result = true;
        try {
            articleMapper.insert(article);
        }catch (Exception e){
            e.printStackTrace();
            result = false;
        }
        return result;
    }

    @Override
    public boolean editArticle(Article article) {
        boolean result = true;
        try {
            articleMapper.updateByPrimaryKeyWithBLOBs(article);
        }catch (Exception e){
            e.printStackTrace();
            result = false;
        }
        return result;
    }

    @Override
    public boolean dropArticle(Integer articleId) {
        boolean result = true;
        Article article = articleMapper.selectByPrimaryKey(articleId);
        byte invalid = 0;
        article.setIsValid(invalid);
        try {
            articleMapper.updateByPrimaryKey(article);
        }catch (Exception e){
            e.printStackTrace();
            result = false;
        }
        return result;
    }

}
