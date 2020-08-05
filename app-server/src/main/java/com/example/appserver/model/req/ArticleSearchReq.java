package com.example.appserver.model.req;

import lombok.Data;

import java.util.List;

/**
 * @author liubinhao
 * @date 2020/8/1
 */
@Data
public class ArticleSearchReq {

    private List<Integer> articleClassifyId;

    private Integer       userId;

    private Integer       articleId;
}
