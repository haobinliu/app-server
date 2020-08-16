package com.example.appserver.controller;

import com.example.appserver.common.CommonResponse;
import com.example.appserver.model.entity.Article;
import com.example.appserver.model.req.ArticleSearchReq;
import com.example.appserver.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author liubinhao
 * @date 2020/8/1
 */

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Resource
    private ArticleService articleService;

    @PostMapping("/list")
    public CommonResponse<List<Article>> exhibitArticle(@RequestBody ArticleSearchReq req){
        List<Article> articles = articleService.exhibitArticle(req);
        return CommonResponse.ok(articles);
    }

    @PostMapping("/publish")
    public CommonResponse<Boolean> publishArticle(@RequestBody Article article){
        boolean result = articleService.publishArticle(article);
        return CommonResponse.ok(result);
    }

    @PostMapping("/edit")
    public CommonResponse<Boolean> editArticle(@RequestBody Article article){
        boolean result = articleService.editArticle(article);
        return CommonResponse.ok(result);
    }

    @PostMapping("/drop")
    public CommonResponse<Boolean> dropArticle(@RequestParam Integer articleId){
        boolean result = articleService.dropArticle(articleId);
        return CommonResponse.ok(result);
    }

    @GetMapping("/favor")
    public CommonResponse<Void> favorArticle(@RequestParam Integer articleId){
        return CommonResponse.ok();
    }

}
