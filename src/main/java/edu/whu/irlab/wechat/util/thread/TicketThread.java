package edu.whu.irlab.wechat.util.thread;

import edu.whu.irlab.wechat.model.token.Ticket;
import edu.whu.irlab.wechat.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Roger on 2016/3/28.
 * 定时获取微信jsapi_ticket的线程
 */
public class TicketThread implements Runnable {
    private static Logger log = LoggerFactory.getLogger(TicketThread.class);

    public static Ticket ticket = null;

    public void run() {
        while (true) {
            try {
                ticket = CommonUtil.getJSAPI_Tiket(TokenThread.token.getAccessToken());
                if (null != ticket) {
                    log.info("获取jsapi_ticket成功，有效时长{}秒 jsapi_ticket:{}", ticket.getExpiresIn(), ticket.getTicket());
                    // 休眠7000秒
                    Thread.sleep((ticket.getExpiresIn() - 200) * 1000);
                } else {
                    // 如果jsapi_ticket为null，60秒后再获取
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
