package edu.whu.irlab.client.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Roger on 2016/6/16.
 */
@Entity
@Table(name = "event_sign")
public class EventSign{
    private Integer eventSignId;
    private Integer eventId;
    private String eventSignPic;
    private APPUser eventSignCreator;
    private Date eventSignCreatetime;
    private String eventSignStatus;
    private String eventSignProcess;
    private String eventSignPraise;
    private Date eventUpdatetime;
    private String text1;
    private String text2;
    private String text3;
    private String text4;
    private String eventMobile;
    private String eventNum;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_sign_id")
    public Integer getEventSignId() {
        return eventSignId;
    }

    public void setEventSignId(Integer eventSignId) {
        this.eventSignId = eventSignId;
    }

    @Column(name = "event_id")
    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    @Column(name = "event_sign_pic")
    public String getEventSignPic() {
        return eventSignPic;
    }

    public void setEventSignPic(String eventSignPic) {
        this.eventSignPic = eventSignPic;
    }

    @ManyToOne
    @JoinColumn(name = "event_sign_creator")
    public APPUser getEventSignCreator() {
        return eventSignCreator;
    }

    public void setEventSignCreator(APPUser eventSignCreator) {
        this.eventSignCreator = eventSignCreator;
    }

    @Column(name = "event_sign_createtime")
    public Date getEventSignCreatetime() {
        return eventSignCreatetime;
    }

    public void setEventSignCreatetime(Date eventSignCreatetime) {
        this.eventSignCreatetime = eventSignCreatetime;
    }

    @Column(name = "event_sign_status")
    public String getEventSignStatus() {
        return eventSignStatus;
    }

    public void setEventSignStatus(String eventSignStatus) {
        this.eventSignStatus = eventSignStatus;
    }

    @Column(name = "event_sign_process")
    public String getEventSignProcess() {
        return eventSignProcess;
    }

    public void setEventSignProcess(String eventSignProcess) {
        this.eventSignProcess = eventSignProcess;
    }

    @Column(name = "event_sign_praise")
    public String getEventSignPraise() {
        return eventSignPraise;
    }

    public void setEventSignPraise(String eventSignPraise) {
        this.eventSignPraise = eventSignPraise;
    }

    @Column(name = "event_updatetime")
    public Date getEventUpdatetime() {
        return eventUpdatetime;
    }

    public void setEventUpdatetime(Date eventUpdatetime) {
        this.eventUpdatetime = eventUpdatetime;
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

    @Column(name = "event_mobile")
    public String getEventMobile() {
        return eventMobile;
    }

    public void setEventMobile(String eventMobile) {
        this.eventMobile = eventMobile;
    }

    @Column(name = "event_num")
    public String getEventNum() {
        return eventNum;
    }

    public void setEventNum(String eventNum) {
        this.eventNum = eventNum;
    }
}
