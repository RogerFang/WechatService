package edu.whu.irlab.client.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Roger on 2016/5/22.
 */
@Entity
@Table(name = "comment_info")
public class CommentInfo {
    private int id;
    private Integer commentReplyId;
    private String commentReplyName;
    private String commentType;
    private String commentMode;
    private String commentContent;
    private APPUser commentCreator;
    private Date commentCreatetime;
    private String commentStatus;
    private Integer infoId;
    private String text1;
    private String text2;
    private String text3;
    private String text4;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "comment_reply_id")
    public Integer getCommentReplyId() {
        return commentReplyId;
    }

    public void setCommentReplyId(Integer commentReplyId) {
        this.commentReplyId = commentReplyId;
    }

    @Column(name = "comment_reply_name")
    public String getCommentReplyName() {
        return commentReplyName;
    }

    public void setCommentReplyName(String commentReplyName) {
        this.commentReplyName = commentReplyName;
    }

    @Column(name = "comment_type")
    public String getCommentType() {
        return commentType;
    }

    public void setCommentType(String commentType) {
        this.commentType = commentType;
    }

    @Column(name = "comment_mode")
    public String getCommentMode() {
        return commentMode;
    }

    public void setCommentMode(String commentMode) {
        this.commentMode = commentMode;
    }

    @Column(name = "comment_content")
    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    @ManyToOne
    @JoinColumn(name = "comment_creator")
    public APPUser getCommentCreator() {
        return commentCreator;
    }

    public void setCommentCreator(APPUser commentCreator) {
        this.commentCreator = commentCreator;
    }

    @Column(name = "comment_createtime")
    public Date getCommentCreatetime() {
        return commentCreatetime;
    }

    public void setCommentCreatetime(Date commentCreatetime) {
        this.commentCreatetime = commentCreatetime;
    }

    @Column(name = "comment_status")
    public String getCommentStatus() {
        return commentStatus;
    }

    public void setCommentStatus(String commentStatus) {
        this.commentStatus = commentStatus;
    }

    @Column(name = "INFO_ID")
    public Integer getInfoId() {
        return infoId;
    }

    public void setInfoId(Integer infoId) {
        this.infoId = infoId;
    }

    @Column(name = "text1")
    public String getText1() {
        return text1;
    }

    public void setText1(String text1) {
        this.text1 = text1;
    }

    @Column(name = "text2")
    public String getText2() {
        return text2;
    }

    public void setText2(String text2) {
        this.text2 = text2;
    }

    @Column(name = "text3")
    public String getText3() {
        return text3;
    }

    public void setText3(String text3) {
        this.text3 = text3;
    }

    @Column(name = "text4")
    public String getText4() {
        return text4;
    }

    public void setText4(String text4) {
        this.text4 = text4;
    }

}
