package edu.whu.irlab.wechat.model.token;

/**
 * Created by Roger on 2016/3/26.
 * JS接口访问凭证 jsapi_ticket
 */
public class Ticket {
    // JS接口访问凭证
    private String ticket;
    // 凭证有效期，单位：秒
    private int expiresIn;

    public String getTicket() {
        return ticket;
    }
    public void setTicket(String ticket) {
        this.ticket = ticket;
    }
    public int getExpiresIn() {
        return expiresIn;
    }
    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }
}
