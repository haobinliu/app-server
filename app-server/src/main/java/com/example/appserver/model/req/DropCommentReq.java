package com.example.appserver.model.req;

import lombok.Data;

/**
 * @author liubinhao
 * @date 2020/8/1
 */
@Data
public class DropCommentReq {
    /**
     * 评论1级 还是2级
     */
    private Integer commentLevel;

    private Integer commentId;
}
