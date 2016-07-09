package edu.whu.irlab.client.web.wechat;

import com.alibaba.fastjson.JSONObject;
import edu.whu.irlab.wechat.common.Configure;
import edu.whu.irlab.wechat.common.Signature;
import edu.whu.irlab.wechat.model.Parameter;
import edu.whu.irlab.wechat.util.CommonUtil;
import edu.whu.irlab.wechat.util.SignUtil;
import edu.whu.irlab.wechat.wcpay.PayCommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Created by Roger on 2016/7/9.
 */
@Controller
@RequestMapping("/testpay")
public class WcPayController {
    @Autowired
    private Parameter config;

    @RequestMapping(method = RequestMethod.GET)
    public String testpay(){
        return "payindex";
    }

    @RequestMapping(value = "/unifiedorder", method = RequestMethod.GET)
    public void unifiedorder(HttpServletRequest request) throws IllegalAccessException {
        SortedMap<Object, Object> parameters = new TreeMap();
        parameters.put("appid", config.getAppId());

        parameters.put("mch_id", config.getMchId());
        parameters.put("nonce_str", SignUtil.create_nonce_str());
        parameters.put("body", "LEO测试");
        parameters.put("out_trade_no", "201412051510");
        parameters.put("total_fee", "1");
        parameters.put("spbill_create_ip", "127.0.0.1");
        parameters.put("notify_url", "http://roger.com/WechatService/testpay/notify");
        parameters.put("trade_type", "JSAPI");
        parameters.put("openid", "oWuwPuEpIhZcy_K9z7hkiWqJYnKA");
        //根据API给的签名规则进行签名
        String sign = Signature.getSign(parameters);
        parameters.put("sign", sign);

        for (Map.Entry<Object, Object> entry: parameters.entrySet()){
            System.out.println(entry.getKey()+"\t"+entry.getValue());
        }

        String requestXML = PayCommonUtil.getRequestXml(parameters);
        System.out.println("requestXML: " + requestXML);
        JSONObject jsonObject = CommonUtil.httpsRequest(Configure.UNIFIED_ORDER_URL, "POST", requestXML);
        System.out.println("jsonObject: " + jsonObject);
    }

    @RequestMapping(value = "/notify", method = RequestMethod.GET)
    public String notifySuccess(){
        return "notify";
    }
}
