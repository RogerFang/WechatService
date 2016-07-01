package edu.whu.irlab.client.entity;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.web.multipart.MultipartFile;


/**
 * Created by Roger on 2016/5/22.
 */
@Entity
@javax.persistence.Table(name = "topic_info")
public class TopicInfo {
    private int id;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @javax.persistence.Column(name = "topic_id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Transient
    public String getDescPic() {
        String pics = this.topicPic;
        if (pics != null) {
            String[] array = pics.split(",");
            if (array.length == 1) {
                return pics;
            } else {
                return array[0];
            }
        }
        return "";
    }

    private BaseCls topicCls;

    @ManyToOne
    @JoinColumn(name = "topic_cls")
    public BaseCls getTopicCls() {
        return topicCls;
    }

    public void setTopicCls(BaseCls topicCls) {
        this.topicCls = topicCls;
    }

    private String topicTitle;

    @Basic
    @javax.persistence.Column(name = "topic_title")
    public String getTopicTitle() {
        return topicTitle;
    }

    public void setTopicTitle(String topicTitle) {
        this.topicTitle = topicTitle;
    }

    private String topicContent;

    @Basic
    @javax.persistence.Column(name = "topic_content")
    public String getTopicContent() {
        return topicContent;
    }

    public void setTopicContent(String topicContent) {
        this.topicContent = topicContent;
    }

    @Transient
    public String getShortContent() {
        return StringUtils.abbreviate(this.topicContent.trim(), 54);
    }

    @Transient
    public String getMediumContent() {
        return StringUtils.abbreviate(this.topicContent.trim(), 200);
    }

    @Transient
    public String getShortTitle() {
        return StringUtils.abbreviate(this.topicContent.trim(), 48);
    }

    private String topicPic;

    @Basic
    @javax.persistence.Column(name = "topic_pic")
    public String getTopicPic() {
        return topicPic;
    }

    public void setTopicPic(String topicPic) {
        this.topicPic = topicPic;
    }

    private APPUser topicCreator;

    @ManyToOne
    @JoinColumn(name = "topic_creator")
    public APPUser getTopicCreator() {
        return topicCreator;
    }

    public void setTopicCreator(APPUser topicCreator) {
        this.topicCreator = topicCreator;
    }

    private Date topicCreatetime;

    @Basic
    @javax.persistence.Column(name = "topic_createtime")
    public Date getTopicCreatetime() {
        return topicCreatetime;
    }

    public void setTopicCreatetime(Date topicCreatetime) {
        this.topicCreatetime = topicCreatetime;
    }

    private String topicStatus;

    @Basic
    @javax.persistence.Column(name = "topic_status")
    public String getTopicStatus() {
        return topicStatus;
    }

    public void setTopicStatus(String topicStatus) {
        this.topicStatus = topicStatus;
    }

    private String topicCommentCount;

    @Basic
    @javax.persistence.Column(name = "topic_comment_count")
    public String getTopicCommentCount() {
        return topicCommentCount;
    }

    public void setTopicCommentCount(String topicCommentCount) {
        this.topicCommentCount = topicCommentCount;
    }

    private String topicPraiseCount;

    @Basic
    @javax.persistence.Column(name = "topic_praise_count")
    public String getTopicPraiseCount() {
        return topicPraiseCount;
    }

    public void setTopicPraiseCount(String topicPraiseCount) {
        this.topicPraiseCount = topicPraiseCount;
    }

    private String topicForwardCount;

    @Basic
    @javax.persistence.Column(name = "topic_forward_count")
    public String getTopicForwardCount() {
        return topicForwardCount;
    }

    public void setTopicForwardCount(String topicForwardCount) {
        this.topicForwardCount = topicForwardCount;
    }

    private String topicForwardId;

    @Basic
    @javax.persistence.Column(name = "topic_forward_id")
    public String getTopicForwardId() {
        return topicForwardId;
    }

    public void setTopicForwardId(String topicForwardId) {
        this.topicForwardId = topicForwardId;
    }

    private String topicForwardContent;

    @Basic
    @javax.persistence.Column(name = "topic_forward_content")
    public String getTopicForwardContent() {
        return topicForwardContent;
    }

    public void setTopicForwardContent(String topicForwardContent) {
        this.topicForwardContent = topicForwardContent;
    }

    private String topicForwardPic;

    @Basic
    @javax.persistence.Column(name = "topic_forward_pic")
    public String getTopicForwardPic() {
        return topicForwardPic;
    }

    public void setTopicForwardPic(String topicForwardPic) {
        this.topicForwardPic = topicForwardPic;
    }

    private String text1;

    @Basic
    @javax.persistence.Column(name = "text1")
    public String getText1() {
        return text1;
    }

    public void setText1(String text1) {
        this.text1 = text1;
    }

    private String text2;

    @Basic
    @javax.persistence.Column(name = "text2")
    public String getText2() {
        return text2;
    }

    public void setText2(String text2) {
        this.text2 = text2;
    }

    private String text3;

    @Basic
    @javax.persistence.Column(name = "text3")
    public String getText3() {
        return text3;
    }

    public void setText3(String text3) {
        this.text3 = text3;
    }

    private String text4;

    @Basic
    @javax.persistence.Column(name = "text4")
    public String getText4() {
        return text4;
    }

    public void setText4(String text4) {
        this.text4 = text4;
    }

    private Timestamp topicLastUpdatetime;

    @Basic
    @javax.persistence.Column(name = "topic_last_updatetime")
    public Timestamp getTopicLastUpdatetime() {
        return topicLastUpdatetime;
    }

    public void setTopicLastUpdatetime(Timestamp topicLastUpdatetime) {
        this.topicLastUpdatetime = topicLastUpdatetime;
    }

    private String spId;

    @Basic
    @javax.persistence.Column(name = "sp_id")
    public String getSpId() {
        return spId;
    }

    public void setSpId(String spId) {
        this.spId = spId;
    }

    @Transient
    public String getText4PicDesc() {
        String pics = this.text4;
        if (pics != null) {
            String[] array = pics.split(",");
            if (array.length == 1) {
                return pics;
            } else {
                return array[0];
            }
        }
        return "";
    }

    private String formatTopicCreatetime;

    @Transient
    public String getFormatTopicCreatetime() {
        return DateFormatUtils.format(topicCreatetime, "yyyy-MM-dd");
    }
}
