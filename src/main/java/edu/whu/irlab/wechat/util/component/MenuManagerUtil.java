package edu.whu.irlab.wechat.util.component;


import com.alibaba.fastjson.JSONObject;
import edu.whu.irlab.wechat.model.component.*;
import edu.whu.irlab.wechat.model.token.Token;
import edu.whu.irlab.wechat.util.CommonUtil;

/**
 * Created by Roger on 2016/3/26.
 */
public class MenuManagerUtil {
    public static final String menu_create_url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
   public static final String appId = "wxe77e6cada6b7344f";
   public static final String appSecret = "fe1b97dd7e20110a7070e055fb8a3f8e";

    // Herpink
    // public static final String appId = "wx1142ce0bdd3e8671";
    // public static final String appSecret = "5bc20582735ea46b3e2d2368928d07eb";


    public static void main(String[] args) {
        Token token = CommonUtil.getToken(appId, appSecret);

        if(null != token){
            int result = createMenu(getMenu(), token.getAccessToken());
        }
    }

    private static Menu getMenu() {

        ViewButton btn11 = new ViewButton();
        btn11.setType("view");
        btn11.setName("会员");
        // btn11.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxe77e6cada6b7344f&redirect_uri=http%3A%2F%2Froger.tunnel.qydev.com%2FWechatService%2Foauth&response_type=code&scope=snsapi_base&state=register#wechat_redirect");
        btn11.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxe77e6cada6b7344f&redirect_uri=http%3A%2F%2Froger.tunnel.qydev.com%2FWechatService%2Foauth&response_type=code&scope=snsapi_base&state=member#wechat_redirect");

        ViewButton btn12 = new ViewButton();
        btn12.setType("view");
        btn12.setName("蜜语");
        btn12.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxe77e6cada6b7344f&redirect_uri=http%3A%2F%2Froger.tunnel.qydev.com%2FWechatService%2Foauth&response_type=code&scope=snsapi_base&state=topic#wechat_redirect");

        ViewButton btn13 = new ViewButton();
        btn13.setType("view");
        btn13.setName("搜蜜");
        btn13.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxe77e6cada6b7344f&redirect_uri=http%3A%2F%2Froger.tunnel.qydev.com%2FWechatService%2Foauth&response_type=code&scope=snsapi_base&state=findFriend#wechat_redirect");

        ViewButton btn14 = new ViewButton();
        btn14.setType("view");
        btn14.setName("app下载");
        btn14.setUrl("http://a.app.qq.com/o/simple.jsp?pkgname=com.kaiyitech.ipink");

        ViewButton btn15 = new ViewButton();
        btn15.setType("view");
        btn15.setName("wcpay");
        btn15.setUrl("http://http://roger.tunnel.qydev.com/WechatService/WechatService/testpay");

        ComplexButton mainBtn1 = new ComplexButton();
        mainBtn1.setName("Herpink");
        mainBtn1.setSub_button(new Button[]{btn11, btn12, btn13, btn14, btn15});


        ViewButton mainBtn2 = new ViewButton();
        mainBtn2.setType("view");
        mainBtn2.setName("粉蜜活动");
        mainBtn2.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxe77e6cada6b7344f&redirect_uri=http%3A%2F%2Froger.tunnel.qydev.com%2FWechatService%2Foauth&response_type=code&scope=snsapi_base&state=eventinfo#wechat_redirect");

        ViewButton mainBtn3 = new ViewButton();
        mainBtn3.setType("view");
        mainBtn3.setName("粉蜜商城");
        mainBtn3.setUrl("http://m.weigouyi.com/160623095250911019.html");


        // CommonButton btn21 = new CommonButton();
        // btn21.setName("fang");
        // btn21.setType("click");
        // btn21.setKey("ir");

        Menu menu = new Menu();
        menu.setButton(new Button[] {mainBtn1, mainBtn2, mainBtn3});

        return menu;
    }

    /**
     * 创建菜单
     *
     * @param menu 菜单实例
     * @param accessToken 有效的access_token
     * @return 0表示成功，其他值表示失败
     */
    public static int createMenu(Menu menu, String accessToken) {
        int result = 0;

        // 拼装创建菜单的url
        String url = menu_create_url.replace("ACCESS_TOKEN", accessToken);
        // 将菜单对象转换成json字符串
        String jsonMenu = JSONObject.toJSON(menu).toString();
        // 调用接口创建菜单
        JSONObject jsonObject = CommonUtil.httpsRequest(url, "POST", jsonMenu);

        if (null != jsonObject) {
            if (0 != jsonObject.getIntValue("errcode")) {
                result = jsonObject.getIntValue("errcode");
                System.out.println("errcode:"+result);
            }
        }else {
            System.out.println("jsonobject null");
        }

        return result;
    }
}
