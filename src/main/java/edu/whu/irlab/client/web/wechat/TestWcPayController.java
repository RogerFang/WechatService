package edu.whu.irlab.client.web.wechat;

import com.alibaba.fastjson.JSONObject;
import edu.whu.irlab.wechat.common.Configure;
import edu.whu.irlab.wechat.common.XMLUtil;
import edu.whu.irlab.wechat.util.CommonUtil;
import edu.whu.irlab.wechat.wcpay.PayCommonUtil;
import org.jdom2.JDOMException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Created by Roger on 2016/7/9.
 */
@Controller
@RequestMapping("/testpay")
public class TestWcPayController {

    @RequestMapping(value = "/payindex", method = RequestMethod.GET)
    public String testpay(){
        System.out.println("pay index-------------------");
        return "payindex";
    }

    @RequestMapping(value = "/orderForActivity", method = RequestMethod.POST)
    @ResponseBody
    public Map<Object, Object> orderForActivity(String fee,
                                                HttpServletRequest request,
                                                HttpSession session) throws JDOMException, IOException {
        String openId = (String) session.getAttribute("openId");
        fee = String.valueOf(new Double(Double.valueOf(fee)*100).intValue());
        System.out.println("fee:" + fee);

        SortedMap<Object, Object> parameters = new TreeMap<>();
        parameters.put("appid", Configure.getAppid());

        parameters.put("mch_id", Configure.getMchid());
        parameters.put("nonce_str", PayCommonUtil.CreateNoncestr());
        parameters.put("body", "付费活动下订单");
        parameters.put("out_trade_no", Long.toString(new Date().getTime()));
        parameters.put("total_fee", "1");
        parameters.put("spbill_create_ip", "127.0.0.1");
        parameters.put("notify_url", "http://roger.tunnel.qydev.com/WechatService/testpay/notify");
        parameters.put("trade_type", "JSAPI");
        parameters.put("openid", openId);
        //根据API给的签名规则进行签名
        String sign = PayCommonUtil.createSign("UTF-8", parameters);
        parameters.put("sign", sign);

        String requestXML = PayCommonUtil.getRequestXml(parameters);
        System.out.println("requestXML: " + requestXML);
        String resultXml = CommonUtil.httpsRequestStr(Configure.UNIFIED_ORDER_URL, "POST", requestXML);
        System.out.println("resultXml: " + resultXml);

        Map<String, String> map = XMLUtil.doXMLParse(resultXml);

        SortedMap<Object,Object> params = new TreeMap<>();
        params.put("appId", Configure.getAppid());
        params.put("timeStamp", Long.toString(new Date().getTime()));
        params.put("nonceStr", PayCommonUtil.CreateNoncestr());
        params.put("package", "prepay_id="+map.get("prepay_id"));
        params.put("signType", "MD5");
        String paySign =  PayCommonUtil.createSign("UTF-8", params);
        params.put("packageValue", "prepay_id="+map.get("prepay_id"));    //这里用packageValue是预防package是关键字在js获取值出错
        params.put("paySign", paySign);                                                          //paySign的生成规则和Sign的生成规则一致
        params.put("sendUrl", "http://roger.tunnel.qydev.com/WechatService/testpay/notify");                               //付款成功后跳转的页面
        String userAgent = request.getHeader("user-agent");
        char agent = userAgent.charAt(userAgent.indexOf("MicroMessenger")+15);
        params.put("agent", new String(new char[]{agent}));//微信版本号，用于前面提到的判断用户手机微信的版本是否是5.0以上版本。

        for (Map.Entry<Object, Object> entry: params.entrySet()){
            System.out.println(entry.getKey()+"\t"+entry.getValue());
        }
        return params;
    }

    @RequestMapping(value = "/unifiedorder", method = RequestMethod.GET)
    @ResponseBody
    public Map<Object, Object> unifiedorder(String openId, HttpServletRequest request) throws IllegalAccessException, JDOMException, IOException {
        openId = "oNuNJv1RXGZfslerdEzC0SkGNeSQ";

        SortedMap<Object, Object> parameters = new TreeMap();
        parameters.put("appid", "wx61afb7c6dddd0806");

        parameters.put("mch_id", Configure.getMchid());
        parameters.put("nonce_str", PayCommonUtil.CreateNoncestr());
        parameters.put("body", "LEO测试");
        parameters.put("out_trade_no", Long.toString(new Date().getTime()));
        parameters.put("total_fee", "1");
        parameters.put("spbill_create_ip", "127.0.0.1");
        parameters.put("notify_url", "http://roger.tunnel.qydev.com/WechatService/testpay/notify");
        parameters.put("trade_type", "JSAPI");
        parameters.put("openid", openId);
        //根据API给的签名规则进行签名
        String sign = PayCommonUtil.createSign("UTF-8", parameters);
        parameters.put("sign", sign);

        // for (Map.Entry<Object, Object> entry: parameters.entrySet()){
        //     System.out.println(entry.getKey()+"\t"+entry.getValue());
        // }

        String requestXML = PayCommonUtil.getRequestXml(parameters);
        System.out.println("requestXML: " + requestXML);
        String resultXml = CommonUtil.httpsRequestStr(Configure.UNIFIED_ORDER_URL, "POST", requestXML);
        System.out.println("resultXml: " + resultXml);

        Map<String, String> map = XMLUtil.doXMLParse(resultXml);

        SortedMap<Object,Object> params = new TreeMap<Object,Object>();
        params.put("appId", Configure.getAppid());
        params.put("timeStamp", Long.toString(new Date().getTime()));
        params.put("nonceStr", PayCommonUtil.CreateNoncestr());
        params.put("package", "prepay_id="+map.get("prepay_id"));
        params.put("signType", "MD5");
        String paySign =  PayCommonUtil.createSign("UTF-8", params);
        params.put("packageValue", "prepay_id="+map.get("prepay_id"));    //这里用packageValue是预防package是关键字在js获取值出错
        params.put("paySign", paySign);                                                          //paySign的生成规则和Sign的生成规则一致
        params.put("sendUrl", "http://roger.tunnel.qydev.com/WechatService/testpay/notify");                               //付款成功后跳转的页面
        String userAgent = request.getHeader("user-agent");
        char agent = userAgent.charAt(userAgent.indexOf("MicroMessenger")+15);
        params.put("agent", new String(new char[]{agent}));//微信版本号，用于前面提到的判断用户手机微信的版本是否是5.0以上版本。

        for (Map.Entry<Object, Object> entry: params.entrySet()){
            System.out.println(entry.getKey()+"\t"+entry.getValue());
        }
        return params;
    }

    @RequestMapping(value = "/notify", method = RequestMethod.GET)
    public String notifySuccess(){
        return "notify";
    }

    @RequestMapping(value = "/testorder", method = RequestMethod.GET)
    public String testorder(HttpServletRequest request, Model model) throws JDOMException, IOException {
        String openId = "oNuNJv1RXGZfslerdEzC0SkGNeSQ";

        SortedMap<Object, Object> parameters = new TreeMap();
        parameters.put("appid", "wx61afb7c6dddd0806");

        parameters.put("mch_id", Configure.getMchid());
        parameters.put("nonce_str", PayCommonUtil.CreateNoncestr());
        parameters.put("body", "LEO测试");
        parameters.put("out_trade_no", "201607091510");
        parameters.put("total_fee", "1");
        parameters.put("spbill_create_ip", "127.0.0.1");
        parameters.put("notify_url", "http://roger.tunnel.qydev.com/WechatService/testpay/notify");
        parameters.put("trade_type", "JSAPI");
        parameters.put("openid", openId);
        //根据API给的签名规则进行签名
        String sign = PayCommonUtil.createSign("UTF-8", parameters);
        parameters.put("sign", sign);

        // for (Map.Entry<Object, Object> entry: parameters.entrySet()){
        //     System.out.println(entry.getKey()+"\t"+entry.getValue());
        // }

        String requestXML = PayCommonUtil.getRequestXml(parameters);
        System.out.println("requestXML: " + requestXML);
        String resultXml = CommonUtil.httpsRequestStr(Configure.UNIFIED_ORDER_URL, "POST", requestXML);
        System.out.println("resultXml: " + resultXml);

        Map<String, String> map = XMLUtil.doXMLParse(resultXml);

        SortedMap<Object,Object> params = new TreeMap<Object,Object>();
        params.put("appId", Configure.getAppid());
        params.put("timeStamp", Long.toString(new Date().getTime()));
        params.put("nonceStr", PayCommonUtil.CreateNoncestr());
        params.put("package", "prepay_id="+map.get("prepay_id"));
        params.put("signType", "MD5");
        String paySign =  PayCommonUtil.createSign("UTF-8", params);
        params.put("packageValue", "prepay_id="+map.get("prepay_id"));    //这里用packageValue是预防package是关键字在js获取值出错
        params.put("paySign", paySign);                                                          //paySign的生成规则和Sign的生成规则一致
        params.put("sendUrl", "http://roger.tunnel.qydev.com/WechatService/testpay/notify");                               //付款成功后跳转的页面
        String userAgent = request.getHeader("user-agent");
        char agent = userAgent.charAt(userAgent.indexOf("MicroMessenger")+15);
        params.put("agent", new String(new char[]{agent}));//微信版本号，用于前面提到的判断用户手机微信的版本是否是5.0以上版本。

        model.addAttribute("orderParam", params);
        return "payorder";
    }
}
