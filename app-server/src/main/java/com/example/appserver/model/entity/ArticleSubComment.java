package com.example.appserver.model.entity;

import java.util.Date;

public class ArticleSubComment {
    private Integer subCommentId;

    private String subCommentContent;

    private Integer parentCommentId;

    private Integer commentUserId;

    private String nickName;

    private Integer commentReplyUserId;

    private String replyUserNickName;

    private Date createTime;

    private Byte isValid;

    private Integer favorCount;

    public Integer getSubCommentId() {
        return subCommentId;
    }

    public void setSubCommentId(Integer subCommentId) {
        this.subCommentId = subCommentId;
    }

    public String getSubCommentContent() {
        return subCommentContent;
    }

    public void setSubCommentContent(String subCommentContent) {
        this.subCommentContent = subCommentContent;
    }

    public Integer getParentCommentId() {
        return parentCommentId;
    }

    public void setParentCommentId(Integer parentCommentId) {
        this.parentCommentId = parentCommentId;
    }

    public Integer getCommentUserId() {
        return commentUserId;
    }

    public void setCommentUserId(Integer commentUserId) {
        this.commentUserId = commentUserId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Integer getCommentReplyUserId() {
        return commentReplyUserId;
    }

    public void setCommentReplyUserId(Integer commentReplyUserId) {
        this.commentReplyUserId = commentReplyUserId;
    }

    public String getReplyUserNickName() {
        return replyUserNickName;
    }

    public void setReplyUserNickName(String replyUserNickName) {
        this.replyUserNickName = replyUserNickName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Byte getIsValid() {
        return isValid;
    }

    public void setIsValid(Byte isValid) {
        this.isValid = isValid;
    }

    public Integer getFavorCount() {
        return favorCount;
    }

    public void setFavorCount(Integer favorCount) {
        this.favorCount = favorCount;
    }
}