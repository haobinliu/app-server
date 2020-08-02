package com.example.appserver.controller;

import com.example.appserver.common.CommonResponse;
import com.example.appserver.model.entity.ArticleComment;
import com.example.appserver.model.req.DropCommentReq;
import com.example.appserver.service.ArticleCommentService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author liubinhao
 * @date 2020/8/1
 */
@RequestMapping("/comment")
@RestController
public class ArticleCommentController {

    @Resource
    private ArticleCommentService articleCommentService;


    @PostMapping("/publish")
    public CommonResponse<ArticleComment> publishComment(@RequestBody ArticleComment comment){
        ArticleComment articleComment = articleCommentService.publishComment(comment);
        return CommonResponse.ok(articleComment);
    }

    @PostMapping("/drop")
    public CommonResponse<Boolean> dropComment(@RequestBody DropCommentReq req){
        boolean b = articleCommentService.dropComment(req);
        return CommonResponse.ok(b);
    }

    @PostMapping("/favor")
    public CommonResponse<Boolean> favorComment(@RequestBody DropCommentReq req){
        boolean b = articleCommentService.favorComment(req);
        return CommonResponse.ok(b);
    }
}
