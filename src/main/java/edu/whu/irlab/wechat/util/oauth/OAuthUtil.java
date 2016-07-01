package edu.whu.irlab.wechat.util.oauth;


import edu.whu.irlab.wechat.util.CommonUtil;

/**
 * Created by Roger on 2016/3/26.
 */
public class OAuthUtil {
    // 不弹出授权页面，直接跳转，只能获取用户openid
    public static final String SNSAPI_BASE_STATE = "snsapi_base";

    //弹出授权页面，可通过openid拿到昵称、性别、所在地。并且，即使在未关注的情况下，只要用户授权，也能获取其信息
    public static final String SNSAPI_USERINFO_STATE = "snsapi_userinfo";

    public static final String SERVICE_PATH = "http://roger.tunnel.qydev.com/WechatService";

    public static final String appId = "wxe77e6cada6b7344f";

    public static void main(String[] args) {

        // 要跳转的
        String requestmapping = "/oauth";

        // 所发布工程的服务器域名 + 工程名 + 要跳转的
        String url = SERVICE_PATH + requestmapping;

        String scope = SNSAPI_BASE_STATE;

        // 重定向后会带上state参数，开发者可以填写a-zA-Z0-9的参数值 （非必填）
        String state = "eventinfo";

        // 无论直接打开还是做页面302重定向时候，必须带此参数 （必填）
        String wechat_redirect = "wechat_redirect";

        /**
         *  结果： https://open.weixin.qq.com/connect/oauth2/authorize?
         * appid=***
         * &redirect_uri=http%3A%2F%2Futown.ngrok.com%2FUTownWeChat%2FOAuthServlet
         * &response_type=code&scope=snsapi_base&state=STATE#wechat_redirect
         */
        System.out.println(url);
        String result = OAuthUtil.getOauthUrl(appId, url, scope, state, wechat_redirect);

        System.out.println(result);

    }

    /**
     *
     * @param appid
     *            公众号的唯一标识 （必填）
     * @param url 服务器域名 + 工程名 + 要跳转的controller
     *            对地址（url）进行URL编码得到的值就是REDIRECT_URI,它表示授权回调地址（OAuthServlet的访问地址）
     *            ，但是要进行URL编码
     * @param scope
     *            应用授权作用域: snsapi_base （不弹出授权页面，直接跳转，只能获取用户openid），
     *            snsapi_userinfo
     *            （弹出授权页面，可通过openid拿到昵称、性别、所在地。并且，即使在未关注的情况下，只要用户授权，也能获取其信息）
     * @param state
     *            重定向后会带上state参数，开发者可以填写a-zA-Z0-9的参数值 （非必填）
     * @param wechat_redirect
     *            无论直接打开还是做页面302重定向时候，必须带此参数 （必填）
     * @return String
     */
    public static String getOauthUrl(String appid, String url, String scope,
                                     String state, String wechat_redirect) {

        // 结果： http%3A%2F%2Futown.ngrok.com%2FUTownWeChat%2FOAuthServlet
        String redirect_uri = CommonUtil.urlEncodeUTF8(url);

        String str = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="
                + appid
                + "&redirect_uri="
                + redirect_uri
                + "&response_type=code"
                + "&scope="
                + scope
                + "&state="
                + state
                + "#" + wechat_redirect;
        return str;
    }
}
