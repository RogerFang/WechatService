package edu.whu.irlab.client.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Roger on 2016/6/23.
 */
@Entity
@Table(name = "base_user_friend")
public class APPUserFriend {
    private Integer fId;
    private APPUser user;
    private APPUser userFriend;
    private Date fCreatetime;
    private Integer fStatus;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "f_id")
    public Integer getfId() {
        return fId;
    }

    public void setfId(Integer fId) {
        this.fId = fId;
    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    public APPUser getUser() {
        return user;
    }

    public void setUser(APPUser user) {
        this.user = user;
    }

    @ManyToOne
    @JoinColumn(name = "user_friend_id")
    public APPUser getUserFriend() {
        return userFriend;
    }

    public void setUserFriend(APPUser userFriend) {
        this.userFriend = userFriend;
    }

    @Column(name = "f_create_time")
    public Date getfCreatetime() {
        return fCreatetime;
    }

    public void setfCreatetime(Date fCreatetime) {
        this.fCreatetime = fCreatetime;
    }

    @Column(name = "f_status")
    public Integer getfStatus() {
        return fStatus;
    }

    public void setfStatus(Integer fStatus) {
        this.fStatus = fStatus;
    }
}
