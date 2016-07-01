package edu.whu.irlab.wechat.util;


import edu.whu.irlab.wechat.util.thread.TicketThread;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * 请求校验工具类
 * @author Tyrion
 *
 */
public class SignUtil {

	public static boolean checkSignature(String token, String signature,String timestamp, String nonce) {
        // 对token、timestamp和nonce按字典排序
        String[] paramArr = new String[] { token, timestamp, nonce };

        String ciphertext = sha1(paramArr);

        // 将sha1加密后的字符串与signature进行对比
        return ciphertext != null ? ciphertext.equals(signature.toUpperCase())
                : false;
    }

    /**
     * sha1 加密.
     *
     * @param paramArr
     *            加密数组
     * @return 加密后字符串
     */
    public static String sha1(String[] paramArr) {
        if (paramArr == null || paramArr.length == 0) {
            return "";
        }
        String content = "";
        // 将排序后的结果拼接成一个字符串
        Arrays.sort(paramArr);
        for (String str : paramArr) {
            content = content.concat(str);
        }
        String ciphertext = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            // 对接后的字符串进行sha1加密
            byte[] digest = md.digest(content.toString().getBytes());
            ciphertext = byteToStr(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return ciphertext;
    }

    /**
     * 将字节数组转换为十六进制字符串
     *
     * @param byteArray
     * @return
     */
    private static String byteToStr(byte[] byteArray) {
        String strDigest = "";
        for (int i = 0; i < byteArray.length; i++) {
            strDigest += byteToHexStr(byteArray[i]);
        }
        return strDigest;
    }

    /**
     * 将字节转换为十六进制字符串
     *
     * @param mByte
     * @return
     */
    private static String byteToHexStr(byte mByte) {
        char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A',
                'B', 'C', 'D', 'E', 'F' };
        char[] tempArr = new char[2];
        tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
        tempArr[1] = Digit[mByte & 0X0F];
        String s = new String(tempArr);
        return s;
    }

    /**
     * 生成jsapi_ticket用的signature,并保存在Map中
     *
     * 一般在jsp页面调用map里的参数进行JS接口的wx_config配置
     *
     * @param url
     *            调用JS接口页面的完整URL
     * @return
     */
    public static Map<String, String> jsTicketSign(String appId, String appSecret, String url) {
        Map<String, String> ret = new HashMap<String, String>();
        String nonce_str = create_nonce_str();
        String timestamp=create_timestamp();
        //注意这里的参数名必须全部小写，且有序
        String sign = "";
        String signature = "";

        // 注意这里的参数名必须全部小写，且必须有序
		/*string1 = "jsapi_ticket=" + TicketThread.ticket.getTicket()
		"bxLdikRXVbTPdHSM05e5u4PsB_pf0HT6hPvJFh1cRnCiYQ8QXDAu69IbDxnEUSQwHjCrxKTMM1ghaTUTJAaLsw"
				+ "&noncestr=" + nonce_str + "&timestamp=" + timestamp
				+ "&url=" + url;
		*/
        String ticket = TicketThread.ticket.getTicket();

        sign = "jsapi_ticket=" + ticket +
                  "&noncestr=" + nonce_str +
                  "&timestamp=" + timestamp+
                  "&url=" + url;

        try {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(sign.getBytes("UTF-8"));
            signature = byteToHex(crypt.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        ret.put("appId", appId);
        ret.put("url", url);
        ret.put("jsapi_ticket", ticket);
        ret.put("nonceStr", nonce_str);
        ret.put("timestamp", timestamp);
        ret.put("signature", signature);

        return ret;
    }

    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

    /**
     * 生成随机字符串。
     *
     * @return
     */
    public  static String create_nonce_str() {
        return UUID.randomUUID().toString();
    }

    /**
     * 取得当前时间戳（秒）。
     *
     * @return
     */
    public static String create_timestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }
}
  