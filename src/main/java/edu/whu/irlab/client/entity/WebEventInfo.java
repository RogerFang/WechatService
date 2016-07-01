package edu.whu.irlab.client.entity;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

/**
 * Created by Roger on 2016/6/16.
 */
public class WebEventInfo {
    private Integer eventCls;
    private String eventTitle;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date eventBegintime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date eventEndtime;
    private String limitNum;
    private String location;
    private String eventPlace;
    private String eventContent;
    private List<MultipartFile> multipartFiles;
    private String eventPic;
    private APPUser eventCreator;

    private Integer picTag;
    private Integer voteTag;

    public Integer getEventCls() {
        return eventCls;
    }

    public void setEventCls(Integer eventCls) {
        this.eventCls = eventCls;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public Date getEventBegintime() {
        return eventBegintime;
    }

    public void setEventBegintime(Date eventBegintime) {
        this.eventBegintime = eventBegintime;
    }

    public Date getEventEndtime() {
        return eventEndtime;
    }

    public void setEventEndtime(Date eventEndtime) {
        this.eventEndtime = eventEndtime;
    }

    public String getLimitNum() {
        return limitNum;
    }

    public void setLimitNum(String limitNum) {
        this.limitNum = limitNum;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEventPlace() {
        return eventPlace;
    }

    public void setEventPlace(String eventPlace) {
        this.eventPlace = eventPlace;
    }

    public String getEventContent() {
        return eventContent;
    }

    public void setEventContent(String eventContent) {
        this.eventContent = eventContent;
    }

    public List<MultipartFile> getMultipartFiles() {
        return multipartFiles;
    }

    public void setMultipartFiles(List<MultipartFile> multipartFiles) {
        this.multipartFiles = multipartFiles;
    }

    public String getEventPic() {
        return eventPic;
    }

    public void setEventPic(String eventPic) {
        this.eventPic = eventPic;
    }

    public APPUser getEventCreator() {
        return eventCreator;
    }

    public void setEventCreator(APPUser eventCreator) {
        this.eventCreator = eventCreator;
    }

    public Integer getPicTag() {
        return picTag;
    }

    public void setPicTag(Integer picTag) {
        this.picTag = picTag;
    }

    public Integer getVoteTag() {
        return voteTag;
    }

    public void setVoteTag(Integer voteTag) {
        this.voteTag = voteTag;
    }
}
