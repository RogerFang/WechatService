package edu.whu.irlab.client.entity;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * 微信用户注册信息
 * Created by Roger on 2016/5/22.
 */
public class BaseUser {
    private String openId;
    private String mobile;
    // 注册时输入的明文密码
    private String password;
    // 短信验证码
    private String captcha;
    private String userAgeDesc;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    @NotNull
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @NotNull
    @Length(min = 6, max = 16)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @NotNull
    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public String getUserAgeDesc() {
        return userAgeDesc;
    }

    public void setUserAgeDesc(String userAgeDesc) {
        this.userAgeDesc = userAgeDesc;
    }
}
