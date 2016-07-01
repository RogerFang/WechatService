package edu.whu.irlab.client.sms;

import cn.com.flaginfo.ws.SmsStub;
import org.springframework.stereotype.Service;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Roger on 2016/6/27.
 */
@Service
public class SmsService {

    public int sendMessage(String mobile, int code){
        try {
            SmsStub stub = new SmsStub("http://sms.api.ums86.com:8899/sms_hb/services/Sms?wsdl");

            SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
            //发送接口
            SmsStub.Sms sms0 = new SmsStub.Sms();

            sms0.setIn0("223290");//企业编号
            sms0.setIn1("wh_apk");//登录名
            sms0.setIn2("20131008pink");//密码
            sms0.setIn3("您的验证码为"+code+"请勿泄露。");//短信内容
            sms0.setIn4(mobile);//手机号码
            sms0.setIn5("000000"+format.format(new Date()));
            sms0.setIn6("");
            sms0.setIn7("1");
            sms0.setIn8("");
            SmsStub.SmsResponse resp = stub.Sms(sms0);
            System.out.println(resp.getOut());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
