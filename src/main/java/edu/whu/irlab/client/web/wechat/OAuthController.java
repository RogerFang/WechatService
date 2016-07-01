package edu.whu.irlab.client.web.wechat;

import edu.whu.irlab.wechat.model.Parameter;
import edu.whu.irlab.wechat.model.oauth.OAuthToken;
import edu.whu.irlab.wechat.model.oauth.SNSUserInfo;
import edu.whu.irlab.wechat.util.oauth.AdvancedUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Roger on 2016/3/26.
 * 网页授权回调Controller
 */
@Controller
public class OAuthController {
    private static Logger logger = LoggerFactory.getLogger(OAuthController.class);

    @Autowired
    private Parameter parameter;

    @RequestMapping("/oauth")
    public String oauth(HttpServletRequest request, RedirectAttributes redirectAttributes){
        logger.info("INFO:/oauth");
        //通过state跳对应的页面
        String state=request.getParameter("state");

        // 用户同意授权后，能获取到code
        String code = request.getParameter("code");
        String openId="";
        String accessToken = "";

        openId=(String) request.getSession().getAttribute("openId");
        // 用户同意授权
        if (code != null && (!code.equals(""))) {
            // 获取网页授权access_token
            OAuthToken oauthToken = AdvancedUtil.getOauth2AccessToken(parameter.getAppId(), parameter.getAppSecret(), code);
            // 网页授权接口访问凭证
            if(oauthToken!=null){
                accessToken = oauthToken.getAccessToken();
            }
            // 用户标识
            if(oauthToken!=null){
                openId = oauthToken.getOpenId();
            }
        }

        // 页面中获取
        redirectAttributes.addFlashAttribute("openId", openId);
        // Controller中获取
        redirectAttributes.addAttribute("openId", openId);

        /*if(state.equals("e")){
            // 获取用户信息
           SNSUserInfo snsUserInfo = AdvancedUtil.getSNSUserInfo(accessToken, openId);
           String nickname = snsUserInfo.getNickname();
           Integer sex = snsUserInfo.getSex();
           String province = snsUserInfo.getProvince();
           String city = snsUserInfo.getCity();
           System.out.println("openId:"+openId);
           System.out.println("nickName:"+nickname);
            System.out.println("sex:"+sex);
            System.out.println("province:"+province);
            System.out.println("city:"+city);
            return "redirect:/";
//            request.getRequestDispatcher("/jsp/Client/register.jsp?openId=" + openId).forward(request, response);
//            response.sendRedirect(ServicePathUtil.URL + "/UTownWeChat/jsp/Client/myDiscount.jsp?wechat_card_js=1");
        }else */
        if (state.equals("member")){
            System.out.println("OAuthController Go To member:"+openId);
            return "redirect:/member";
        }else if (state.equals("register")){
            System.out.println("OAuthController Go To register:"+openId);
            return "redirect:/member/register";
        }else if (state.equals("findFriend")){
            System.out.println("OAuthController Go To find friend: "+openId);
            return "redirect:/member/findFriend";
        }else if (state.equals("eventinfo")){
            System.out.println("OAuthController Go To eveninfo: "+openId);
            return "redirect:/eventinfo/list";
        }else if (state.equals("topic")){
            System.out.println("OAuthController Go to topic: "+openId);
            return "redirect:/topic/list";
        }
        return null;
    }
}
