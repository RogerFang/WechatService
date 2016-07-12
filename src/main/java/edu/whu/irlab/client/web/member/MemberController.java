package edu.whu.irlab.client.web.member;

import edu.whu.irlab.client.entity.*;
import edu.whu.irlab.client.service.EventInfoService;
import edu.whu.irlab.client.service.EventSignService;
import edu.whu.irlab.client.service.TopicInfoService;
import edu.whu.irlab.client.service.UserService;
import edu.whu.irlab.client.sms.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

/**
 * Created by Roger on 2016/5/15.
 */
@Controller
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private UserService userService;

    @Autowired
    private TopicInfoService topicInfoService;

    @Autowired
    private EventInfoService eventInfoService;

    @Autowired
    private EventSignService eventSignService;

    @Autowired
    private SmsService smsService;

    @RequestMapping(method = RequestMethod.GET)
    public String member(String openId,
                         HttpSession session,
                         Model model){
        if (openId!=null){
            session.setAttribute("openId", openId);
        }
        WechatUser wechatUser = userService.findWechatUserByOpenId(openId);
        APPUser appUser = userService.findAPPUserByAppId(wechatUser.getAppId());
        model.addAttribute("user", appUser);
        return "member/member";
    }

    @RequestMapping(value = "uploadPhoto", method = RequestMethod.GET)
    public String uploadPhoto(){
        return "member/uploadPhoto";
    }

    @RequestMapping(value = "uploadPhoto", method = RequestMethod.POST)
    public String uploadPhoto(HttpServletRequest request,
                              HttpSession session,
                              MultipartFile file) throws IOException {
        String openId = (String) session.getAttribute("openId");
        WechatUser wechatUser = userService.findWechatUserByOpenId(openId);
        String baseDir = request.getSession().getServletContext().getRealPath("/") + File.separator;
        if (!file.isEmpty()){
            String imgPath =  "base" + File.separator + "user"+ File.separator + UUID.randomUUID() + ".jpg";
            FileOutputStream fos = new FileOutputStream(baseDir + imgPath);
            fos.write(file.getBytes());
            fos.close();

            userService.updateUserPhoto(imgPath, wechatUser.getAppId());
        }
        return "redirect:/member";
    }

    @RequestMapping(value = "/completeInfo", method = RequestMethod.GET)
    public String completeInfo(HttpSession session,
                               Model model){
        String openId = (String) session.getAttribute("openId");
        WechatUser wechatUser = userService.findWechatUserByOpenId(openId);
        APPUser appUser = userService.findAPPUserByAppId(wechatUser.getAppId());
        model.addAttribute("user", appUser);
        return "member/completeInfo";
    }

    @RequestMapping(value = "/completeInfo", method = RequestMethod.POST)
    public String completeInfo(APPUser appUser,
                               HttpSession session){
        System.out.println(appUser.getUserAgeDesc().substring(0, 4));
        System.out.println(appUser.getUserStar());
        System.out.println(appUser.getUserCareer());
        System.out.println(appUser.getText4());
        userService.completeInfo(appUser);
        return "redirect:/member";
    }

    @RequestMapping(value = "/mytopic", method = RequestMethod.GET)
    public String mytopic(HttpSession session,
                          Model model){
        String openId = (String) session.getAttribute("openId");
        System.out.println("openId:" + openId);
        WechatUser wechatUser = userService.findWechatUserByOpenId(openId);
        APPUser appUser = userService.findAPPUserByAppId(wechatUser.getAppId());
        System.out.println(appUser.getId());
        List<TopicInfo> topicInfos = topicInfoService.findByTopicCreator(appUser);
        model.addAttribute("topicInfos", topicInfos);
        return "member/mytopic";
    }

    /**
     * 进入注册页面, 首次进入时将openId放入session中
     * @param session
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(HttpSession session){
        System.out.println("To register: "+ session.getAttribute("openId"));
        return "member/register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@Valid BaseUser user,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes,
                           HttpSession session){
        if (bindingResult.hasErrors()){
            System.out.println(bindingResult.getErrorCount());
            return "redirect: register";
        }
        user.setOpenId((String) session.getAttribute("openId"));
        System.out.println(user.getMobile());
        if (userService.findAPPUserByMobile(user.getMobile())!=null){
            System.out.println("该手机已注册");
            redirectAttributes.addFlashAttribute("msg", "该手机号已注册,请勿重复注册");
            return "redirect:login";
        }

        if (userService.findWechatUserByOpenId(user.getOpenId()) != null){
            System.out.println("该微信账号已被绑定");
            redirectAttributes.addFlashAttribute("msg", "该微信账号已被绑定,请勿重复注册");
            return "redirect:register";
        }

        System.out.println("用户注册!");
        userService.registerUser(user);
        return "redirect:/member";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(HttpSession session){
        System.out.println("To login: " + session.getAttribute("openId"));
        return "member/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(BaseUser user,
                        HttpSession session,
                        RedirectAttributes redirectAttributes){
        String openId = (String) session.getAttribute("openId");
        user.setOpenId(openId);
        Map<String, Object> map = userService.loginUser(user);
        if ((Boolean) map.get("success")){
            redirectAttributes.addAttribute("openId", openId);
            return "redirect:/member";
        }else {
            redirectAttributes.addFlashAttribute("msg", map.get("msg"));
            return "redirect:login";
        }
    }

    /**
     * 搜蜜
     * @return
     */
    @RequestMapping(value = "/findFriend", method = RequestMethod.GET)
    public String findFriend(){
        return "member/findFriend";
    }

    @RequestMapping(value = "/findFriend", method = RequestMethod.POST)
    public String findFriend(@RequestParam(value = "page", defaultValue = "1") int pageNumber,
                             @RequestParam(value = "page.size", defaultValue = "10") int pageSize,
                             APPUser appUser,
                             Model model){
        System.out.println(appUser.getUserAgeDesc());
        System.out.println(appUser.getUserStar());
        System.out.println(appUser.getUserCareer());
        System.out.println(appUser.getText4());

        Page<APPUser> auPage = userService.findFriend(pageNumber, pageSize, appUser);
        model.addAttribute("auPage", auPage);
        return "member/findFriendList";
    }


    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public String download(String openId, HttpSession session){
        session.setAttribute("openId", openId);
        return "member/download";
    }

    @RequestMapping(value = "/myactivity", method = RequestMethod.GET)
    public String myactivity(HttpSession session, Model model){
        WechatUser wechatUser = userService.findWechatUserByOpenId((String) session.getAttribute("openId"));
        APPUser appUser = userService.findAPPUserByAppId(wechatUser.getAppId());

        // 我发起的活动
        List<EventInfo> eventInfoList = eventInfoService.getEventsByEventCreator(appUser);
        // 我参加报名的活动
        List<EventSign> eventSignList = eventSignService.getEventSignByCreator(appUser);
        List<EventInfo> eventInfoSignList = new ArrayList<>();
        for (EventSign es: eventSignList){
            eventInfoSignList.add(eventInfoService.findEntity(es.getEventId()));
        }

        model.addAttribute("eventInfoList", eventInfoList);
        model.addAttribute("eventInfoSignList", eventInfoSignList);
        model.addAttribute("today", new Date());
        return "member/myactivity";
    }

    @RequestMapping(value = "/enrollDetail/{id}", method = RequestMethod.GET)
    public String enrollDetail(@PathVariable Integer id,
                               Model model){
        List<EventSign> eventSignList = eventSignService.getEventSignByEventId(id);
        EventInfo eventInfo = eventInfoService.findEntity(id);
        model.addAttribute("eventSignList", eventSignList);
        model.addAttribute("eventInfo", eventInfo);
        return "member/enrollDetail";
    }

    @RequestMapping(value = "/myfriend", method = RequestMethod.GET)
    public String myfriend(@RequestParam(value = "page", defaultValue = "1") int pageNumber,
                           @RequestParam(value = "page.size", defaultValue = "10") int pageSize,
                           HttpSession session,
                           Model model){
        WechatUser wechatUser = userService.findWechatUserByOpenId((String) session.getAttribute("openId"));
        APPUser appUser = userService.findAPPUserByAppId(wechatUser.getAppId());
        List<APPUserFriend> userFriendList = userService.getMyFriend(pageNumber, pageSize, appUser).getContent();
        model.addAttribute("userFriendList", userFriendList);
        return "member/myfriend";
    }

    @RequestMapping(value = "/username", method = RequestMethod.GET)
    public String username(HttpSession session, Model model){
        WechatUser wechatUser = userService.findWechatUserByOpenId((String) session.getAttribute("openId"));
        APPUser appUser = userService.findAPPUserByAppId(wechatUser.getAppId());
        model.addAttribute("user", appUser);
        return "member/username";
    }

    @RequestMapping(value = "/username", method = RequestMethod.POST)
    public String username(HttpSession session, String username){
        WechatUser wechatUser = userService.findWechatUserByOpenId((String) session.getAttribute("openId"));
        APPUser appUser = userService.findAPPUserByAppId(wechatUser.getAppId());
        appUser.setUserName(username);
        userService.updateUsername(appUser);
        return "redirect:/member";
    }

    @RequestMapping(value = "/sendCaptcha", method = RequestMethod.POST)
    @ResponseBody
    public String sendCaptcha(String mobile){
        int code = (int) (Math.random() * 900000 + 100000);
        smsService.sendMessage(mobile, code);
        return String.valueOf(code);
    }
}
