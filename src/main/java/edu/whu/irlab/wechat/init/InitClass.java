package edu.whu.irlab.wechat.init;


import edu.whu.irlab.wechat.common.Configure;
import edu.whu.irlab.wechat.util.thread.TokenThread;

/**
 * 微信启动的入口
 * Created by Roger on 2016/3/28.
 */
public class InitClass {

    private String appId;
    private String appSecret;

    public InitClass(){
        this.appId = Configure.getAppid();
        this.appSecret = Configure.getAppSecret();
    }

    public void init(){
        System.out.println("INFO:this is init ! TokenThread start");
        TokenThread.appId = appId;
        TokenThread.appSecret = appSecret;
        if ("".equals(TokenThread.appId) || "".equals(TokenThread.appSecret)) {
        } else {
            new Thread(new TokenThread()).start();
        }
    }
}
