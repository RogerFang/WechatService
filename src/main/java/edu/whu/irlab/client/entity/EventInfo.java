package edu.whu.irlab.client.entity;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 活动信息
 *
 * Created by Roger on 2016/6/7.
 */
@Entity
@Table(name = "event_info")
public class EventInfo {
    private static final int MAXWIDTH = 100;
    private int id;
    private Integer eventCls;
    private String eventType;
    private String eventTitle;
    private String eventContent;
    private String eventPic;
    private APPUser eventCreator;
    private Date eventCreatetime;
    private Date eventBegintime;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Transient
    public String getShortTitle() {
        if (this.eventTitle == null) {
            return "";
        }
        return StringUtils.abbreviate(this.eventTitle, 20);
    }

    @Transient
    public String getDescAbstract() {
        if (eventContent == null) {
            return "";
        }
        eventContent = eventContent.trim();
        eventContent = eventContent.replaceAll("<[^>]+>", " ").replaceAll("\\s*|\t|\r|\n", "");
        if (eventContent.length() > MAXWIDTH) {
            return StringUtils.abbreviate(eventContent, MAXWIDTH);
        }
        return eventContent;
    }

    @Basic
    @javax.persistence.Column(name = "event_cls")
    public Integer getEventCls() {
        return eventCls;
    }

    public void setEventCls(Integer eventCls) {
        this.eventCls = eventCls;
    }

    @Basic
    @javax.persistence.Column(name = "event_type")
    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    @Basic
    @javax.persistence.Column(name = "event_title")
    public String getEventTitle() {
        if (eventTitle == null) {
            return "";
        }
        return eventTitle.trim();
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    @Basic
    @javax.persistence.Column(name = "event_content")
    public String getEventContent() {
        return eventContent;
    }

    public void setEventContent(String eventContent) {
        this.eventContent = eventContent;
    }

    @Transient
    public String getDescPic() {
        String pics = this.getEventPic();
        if (pics != null) {
            String[] array = pics.split(",");
            return array[0];
        }
        return "";
    }

    @Basic
    @javax.persistence.Column(name = "event_pic")
    public String getEventPic() {
        return eventPic;
    }

    public void setEventPic(String eventPic) {
        this.eventPic = eventPic;
    }

    @ManyToOne
    @JoinColumn(name = "event_creator")
    public APPUser getEventCreator() {
        return eventCreator;
    }

    public void setEventCreator(APPUser eventCreator) {
        this.eventCreator = eventCreator;
    }

    @Basic
    @javax.persistence.Column(name = "event_createtime")
    public Date getEventCreatetime() {
        return eventCreatetime;
    }

    public void setEventCreatetime(Date eventCreatetime) {
        this.eventCreatetime = eventCreatetime;
    }

    @Basic
    @javax.persistence.Column(name = "event_begintime")
    public Date getEventBegintime() {
        return eventBegintime;
    }

    public void setEventBegintime(Date eventBegintime) {
        this.eventBegintime = eventBegintime;
    }

    private Date eventEndtime;

    @Basic
    @javax.persistence.Column(name = "event_endtime")
    public Date getEventEndtime() {
        return eventEndtime;
    }

    public void setEventEndtime(Date eventEndtime) {
        this.eventEndtime = eventEndtime;
    }

    private String eventStatus;

    @Basic
    @javax.persistence.Column(name = "event_status")
    public String getEventStatus() {
        return eventStatus;
    }

    public void setEventStatus(String eventStatus) {
        this.eventStatus = eventStatus;
    }

    private String eventProcess;

    @Basic
    @javax.persistence.Column(name = "event_process")
    public String getEventProcess() {
        return eventProcess;
    }

    public void setEventProcess(String eventProcess) {
        this.eventProcess = eventProcess;
    }

    private String eventCommentCount;

    @Basic
    @javax.persistence.Column(name = "event_comment_count")
    public String getEventCommentCount() {
        return eventCommentCount;
    }

    public void setEventCommentCount(String eventCommentCount) {
        this.eventCommentCount = eventCommentCount;
    }

    private String eventMemberCount;

    @Basic
    @javax.persistence.Column(name = "event_member_count")
    public String getEventMemberCount() {
        return eventMemberCount;
    }

    public void setEventMemberCount(String eventMemberCount) {
        this.eventMemberCount = eventMemberCount;
    }

    private String spProvince;

    @Basic
    @javax.persistence.Column(name = "sp_province")
    public String getSpProvince() {
        return spProvince;
    }

    public void setSpProvince(String spProvince) {
        this.spProvince = spProvince;
    }

    private String spCity;

    @Basic
    @javax.persistence.Column(name = "sp_city")
    public String getSpCity() {
        return spCity;
    }

    public void setSpCity(String spCity) {
        this.spCity = spCity;
    }

    private String spArea;

    @Basic
    @javax.persistence.Column(name = "sp_area")
    public String getSpArea() {
        return spArea;
    }

    public void setSpArea(String spArea) {
        this.spArea = spArea;
    }

    private String eventPlace;

    @Basic
    @javax.persistence.Column(name = "event_place")
    public String getEventPlace() {
        return eventPlace;
    }

    public void setEventPlace(String eventPlace) {
        this.eventPlace = eventPlace;
    }

    private String eventCoordinate;

    @Basic
    @javax.persistence.Column(name = "event_coordinate")
    public String getEventCoordinate() {
        return eventCoordinate;
    }

    public void setEventCoordinate(String eventCoordinate) {
        this.eventCoordinate = eventCoordinate;
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

    private String limitNum;

    @Basic
    @javax.persistence.Column(name = "limit_num")
    public String getLimitNum() {
        return limitNum;
    }

    public void setLimitNum(String limitNum) {
        this.limitNum = limitNum;
    }

    private long beginIntervalDays;

    @Transient
    public long getBeginIntervalDays() {
        Date now = new Date();
        return (eventBegintime.getTime() - now.getTime())/(1000*60*60*24) + 1;
    }

    private long endIntervalDays;

    @Transient
    public long getEndIntervalDays() {
        Date now = new Date();
        return (eventEndtime.getTime() - now.getTime())/(1000*60*60*24) + 1;
    }

    private int beginMonth;

    @Transient
    public int getBeginMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(eventBegintime);
        return calendar.get(Calendar.MONTH) + 1;
    }

    private int beginDayOfMonth;

    @Transient
    public int getBeginDayOfMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(eventBegintime);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    private String beginDayOfWeek;

    @Transient
    public String getBeginDayOfWeek() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(eventBegintime);
        int weekDay = calendar.get(Calendar.DAY_OF_WEEK);
        if (Calendar.MONDAY == weekDay) return "周一";
        if (Calendar.TUESDAY == weekDay) return "周二";
        if (Calendar.WEDNESDAY == weekDay) return "周三";
        if (Calendar.THURSDAY == weekDay) return "周四";
        if (Calendar.FRIDAY == weekDay) return "周五";
        if (Calendar.SATURDAY == weekDay) return "周六";
        if (Calendar.SUNDAY == weekDay) return "周日";

        return "";
    }

    private String formatEndTime;

    @Transient
    public String getFormatEndTime() {
        return DateFormatUtils.format(eventEndtime, "yyyy-MM-dd");
    }
}

