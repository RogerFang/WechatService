package edu.whu.irlab.wechat.service.impl;

import edu.whu.irlab.wechat.model.Parameter;
import edu.whu.irlab.wechat.model.response.Article;
import edu.whu.irlab.wechat.model.response.NewsMessage;
import edu.whu.irlab.wechat.model.response.TextMessage;
import edu.whu.irlab.wechat.service.WeChatService;
import edu.whu.irlab.wechat.util.MessageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Roger on 2016/3/26.
 */
@Service
public class WeChatServiceImpl implements WeChatService {
    private static Logger logger = LoggerFactory.getLogger(WeChatServiceImpl.class);

    @Autowired
    private Parameter parameter;

    public String processRequest(HttpServletRequest request) throws Exception {
        String respMessage = null;
        try {
            // xml请求解析
            Map<String, String> requestMap = MessageUtil.parseXml(request);

            // 发送方帐号（open_id）
            String fromUserName = requestMap.get("FromUserName");
            // 公众帐号
            String toUserName = requestMap.get("ToUserName");
            // 消息类型
            String msgType = requestMap.get("MsgType");

            // 事件
            if (msgType.equals(MessageUtil.REQ_TYPE_EVENT)) {
                // 事件类型
                String eventType = requestMap.get("Event");
                // 订阅
                if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
                    String respContent = "Thank you for you subscribe";
                    // 回复文本消息
                    TextMessage textMessage = new TextMessage();
                    textMessage.setToUserName(fromUserName);
                    textMessage.setFromUserName(toUserName);
                    textMessage.setCreateTime(new Date().getTime());
                    textMessage.setMsgType(MessageUtil.RES_TYPE_TEXT);
                    textMessage.setContent(respContent);
                    respMessage = MessageUtil.messageToXml(textMessage);
                }
                // 自定义菜单点击事件
                else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
                    String eventKey = requestMap.get("EventKey");
                    if (eventKey.equals("enroll")){
                        String openId = fromUserName;
                        List<Article> articleList = new ArrayList<Article>();
                        Article article = new Article();
                        article.setTitle("title");
                        article.setPicUrl("picUrl");
                        article.setDescription("desc");
                        // 附带openId
                        article.setUrl(parameter.getServicePath() + "/toEnroll?openId=" + openId);
                        System.out.println("article url:"+ parameter.getServicePath() + "/toEnroll?openId=" + openId);
                        articleList.add(article);
                        respMessage = makeArticle(articleList, fromUserName, toUserName);
                    }
                }else if (eventType.equals(MessageUtil.EVENT_TYPE_VIEW)){
                    String openId = fromUserName;
                    String eventKey = requestMap.get("EventKey");
//                    request.setAttribute("openId", openId);
//                    request.getSession().setAttribute("openId", openId);
                    System.out.println("VIEW openId:" + openId + "  eventKey:" + eventKey);
                }
            }else if(msgType.equals(MessageUtil.REQ_TYPE_TEXT)){
                // 用户发过来的文本
                String reqContent = requestMap.get("Content").toLowerCase();
                if (reqContent.equals("openid")){
                    respMessage = makeText(fromUserName, toUserName, "亲,请先报名参加活动并分享3次再领奖哦!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return respMessage;
    }

    // 创建图文消息
    private static String makeArticle(List<Article> articleList, String fromUserName, String toUserName) {
        NewsMessage newsMessage = new NewsMessage();
        // 原事件消息的fromUserName即是openId
        newsMessage.setToUserName(fromUserName);
        newsMessage.setFromUserName(toUserName);
        newsMessage.setMsgType(MessageUtil.RES_TYPE_NEWS);
        newsMessage.setCreateTime(new Date().getTime());
        newsMessage.setArticleCount(articleList.size());
        newsMessage.setArticles(articleList);
        return MessageUtil.messageToXml(newsMessage);
    }

    // 创建文本消息
    private static String makeText(String fromUserName, String toUserName, String respContent){
        // 回复文本消息
        TextMessage textMessage = new TextMessage();
        textMessage.setToUserName(fromUserName);
        textMessage.setFromUserName(toUserName);
        textMessage.setCreateTime(new Date().getTime());
        textMessage.setMsgType(MessageUtil.RES_TYPE_TEXT);
        textMessage.setContent(respContent);
        return MessageUtil.messageToXml(textMessage);
    }

}
