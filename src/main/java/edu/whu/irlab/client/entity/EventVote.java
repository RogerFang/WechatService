package edu.whu.irlab.client.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Roger on 2016/6/16.
 */
@Entity
@Table(name = "event_vote")
public class EventVote {
    private Integer eventVoteId;
    // 投票人
    private APPUser voteCreator;
    // 被投票人
    private APPUser voteUser;
    private Integer voteEventId;
    private String text1;
    private String text2;
    private String text3;
    private String text4;
    private Date voteDate;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_vote_id")
    public Integer getEventVoteId() {
        return eventVoteId;
    }

    public void setEventVoteId(Integer eventVoteId) {
        this.eventVoteId = eventVoteId;
    }

    @ManyToOne
    @JoinColumn(name = "vote_creator")
    public APPUser getVoteCreator() {
        return voteCreator;
    }

    public void setVoteCreator(APPUser voteCreator) {
        this.voteCreator = voteCreator;
    }

    @ManyToOne
    @JoinColumn(name = "vote_user")
    public APPUser getVoteUser() {
        return voteUser;
    }

    public void setVoteUser(APPUser voteUser) {
        this.voteUser = voteUser;
    }

    @Column(name = "vote_event_id")
    public Integer getVoteEventId() {
        return voteEventId;
    }

    public void setVoteEventId(Integer voteEventId) {
        this.voteEventId = voteEventId;
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

    @Column(name = "vote_date")
    public Date getVoteDate() {
        return voteDate;
    }

    public void setVoteDate(Date voteDate) {
        this.voteDate = voteDate;
    }
}
