package edu.whu.irlab.wechat.model;

import org.springframework.stereotype.Component;

/**
 * Created by Roger on 2016/3/26.
 */
@Component
public class Parameter {
    private String token;
    private String appId;
    private String appSecret;
    private String domain;
    private String servicePath;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getServicePath() {
        return servicePath;
    }

    public void setServicePath(String servicePath) {
        this.servicePath = servicePath;
    }

}
