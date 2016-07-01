package edu.whu.irlab.wechat.util.thread;

import edu.whu.irlab.wechat.model.token.Token;
import edu.whu.irlab.wechat.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Roger on 2016/3/28.
 * 定时获取微信access_token线程
 */
public class TokenThread implements Runnable{
    private static Logger log = LoggerFactory.getLogger(TokenThread.class);
    // 第三方用户唯一凭证
    public static String appId = "";
    // 第三方用户唯一凭证密钥
    public static String appSecret = "";
    // 存储access_token
    public static Token token = null;

    public void run() {

        Thread ticketThread = new Thread(new TicketThread());

        while (true) {
            try {
                token = CommonUtil.getToken(appId, appSecret);
                if (null != token) {
                    log.info("获取access_token成功，有效时长{}秒 token:{}", token.getExpiresIn(), token.getAccessToken());
                    //获取jsapi_ticket
                    if (!ticketThread.isAlive()) {
                        ticketThread.start();
                    }
                    // 休眠7000秒
                    Thread.sleep((token.getExpiresIn() - 200) * 1000);
                } else {
                    // 如果access_token为null，60秒后再获取
                    Thread.sleep(60 * 1000);
                }
            } catch (InterruptedException e) {
                try {
                    Thread.sleep(60 * 1000);
                } catch (InterruptedException e1) {
                    log.error("{}", e1);
                }
                log.error("{}", e);
            }
        }
    }
}
