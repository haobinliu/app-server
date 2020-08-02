package com.example.appserver.controller;

import com.example.appserver.common.CommonResponse;
import com.example.appserver.model.entity.Article;
import com.example.appserver.model.req.ArticleSearchReq;
import org.springframework.web.bind.annotation.*;

/**
 * @author liubinhao
 * @date 2020/8/1
 */

@RestController
@RequestMapping("/article")
public class ArticleContrller {

    @PostMapping("/list")
    public CommonResponse exhibitArticle(@RequestBody ArticleSearchReq req){
        return CommonResponse.ok();
    }

    @PostMapping("/publish")
    public CommonResponse publishArticle(@RequestBody Article article){
        return CommonResponse.ok();
    }

    @PostMapping("/edit")
    public CommonResponse editArticle(@RequestBody Article article){
        return CommonResponse.ok();
    }

    @PostMapping("/drop")
    public CommonResponse dropArticle(@RequestParam Integer articleId){
        return CommonResponse.ok();
    }

    @GetMapping("/favor")
    public CommonResponse favorArticle(@RequestParam Integer articleId){
        return CommonResponse.ok();
    }

}
