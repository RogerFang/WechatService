package edu.whu.irlab.wechat.service;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Roger on 2016/3/26.
 */
public interface WeChatService {
    //处理用户发送信息
    public String processRequest(HttpServletRequest request)throws Exception;
}
