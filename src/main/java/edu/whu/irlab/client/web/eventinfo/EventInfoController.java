package edu.whu.irlab.client.web.eventinfo;

import com.google.common.base.Joiner;
import edu.whu.irlab.client.comparator.EventSignComparator;
import edu.whu.irlab.client.entity.*;
import edu.whu.irlab.client.service.*;
import edu.whu.irlab.wechat.util.oauth.OAuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springside.modules.web.Servlets;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

/**
 * Created by Roger on 2016/6/7.
 */
@Controller
@RequestMapping(value = "/eventinfo")
public class EventInfoController {

    private static final String PAGE_SIZE = "10";

    private static Map<String, String> CLASSMAP = null;

    static {
        synchronized (EventInfoController.class) {
            if (CLASSMAP == null) {
                CLASSMAP = new HashMap<String, String>();
                // 女学馆、美人计、粉妈帮、闺蜜沙龙
                CLASSMAP.put("1", "女学馆");
                CLASSMAP.put("5", "美人计");
                CLASSMAP.put("3", "粉妈帮");
                CLASSMAP.put("4", "闺蜜沙龙");
            }
        }
    }

    @Autowired
    private EventInfoService eiService;

    @Autowired
    private CommentInfoService ciService;

    @Autowired
    private UserService userService;

    @Autowired
    private EventSignService eventSignService;

    @Autowired
    private EventVoteService eventVoteService;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String entry(@RequestParam(value = "page", defaultValue = "1") int pageNumber,
                        @RequestParam(value = "page.size", defaultValue = PAGE_SIZE) int pageSize,
                        Model model) {
        Page<EventInfo> eiPage = eiService.getEventsList(pageNumber, pageSize);
        model.addAttribute("eiPage", eiPage);
        model.addAttribute("page", pageNumber);
        System.out.println(eiPage.getContent().size());
        return "event/eventlist";
    }

    @RequestMapping(value = "list", method = RequestMethod.POST)
    public String entry(@RequestParam(value = "page", defaultValue = "1") int pageNumber,
                        @RequestParam(value = "page.size", defaultValue = PAGE_SIZE) int pageSize,
                        @RequestParam(value = "eventCls", defaultValue = "") String eventCls,
                        @RequestParam(value = "eventArea", defaultValue = "") String eventArea,
                        @RequestParam(value = "eventSort", defaultValue = "1") String eventSort,
                        @RequestParam(value = "eventPay", defaultValue = "") String eventPay,
                        @RequestParam(value = "query", defaultValue = "") String query,
                        Model model) {

        System.out.println("cls:"+eventCls+"\t"+"area:"+eventArea+"\t"+"sort:"+eventSort+"\t"+"pay:"+eventPay+"\t"+"query:"+query);
        Page<EventInfo> eiPage = eiService.getEventsWithCondition(pageNumber, pageSize, eventCls, eventArea, eventSort, eventPay, query);
        model.addAttribute("eiPage", eiPage);
        if (!eventCls.equals("")){
            model.addAttribute("eventCls", "cls_"+eventCls);
        }else {
            model.addAttribute("eventCls", "");
        }
        model.addAttribute("eventArea", "");
        model.addAttribute("eventSort", "sort_"+eventSort);
        if (!eventPay.equals("")){
            model.addAttribute("eventPay", "pay_"+eventPay);
        }else {
            model.addAttribute("eventPay", "");
        }

        model.addAttribute("page", pageNumber);
        model.addAttribute("query", query);
        return "event/eventlist";
    }

    @RequestMapping(value = "/loadList", method = RequestMethod.POST)
    @ResponseBody
    public List<EventInfo> loadList(@RequestParam(value = "page", defaultValue = "1") int pageNumber,
                                    @RequestParam(value = "page.size", defaultValue = PAGE_SIZE) int pageSize,
                                    @RequestParam(value = "eventCls", defaultValue = "") String eventCls,
                                    @RequestParam(value = "eventArea", defaultValue = "") String eventArea,
                                    @RequestParam(value = "eventSort", defaultValue = "1") String eventSort,
                                    @RequestParam(value = "eventPay", defaultValue = "") String eventPay,
                                    @RequestParam(value = "query", defaultValue = "") String query){
        System.out.println("Ajax load list~~"+pageNumber);
        Page<EventInfo> eiPage = eiService.getEventsWithCondition(pageNumber, pageSize, eventCls, eventArea, eventSort, eventPay, query);

        return eiPage.getContent();
    }

    /*@RequestMapping(value = "typelist")
    public String listclass(@RequestParam(value = "page", defaultValue = "1") int pageNumber,
                            @RequestParam(value = "page.size", defaultValue = PAGE_SIZE) int pageSize,
                            @RequestParam(value = "eventType", defaultValue = "1") String eventType, ServletRequest request,
                            Model model) {
        System.out.println(pageNumber);
        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "eventtype_");
        Page<EventInfo> eiPage = eiService.getEventsWithType(pageNumber, pageSize, eventType);
        model.addAttribute("eiPage", eiPage);
        model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "eventtype_"));
        return "event/simplelist";
    }*/

    /*@RequestMapping(value = "paralist")
    public String listWithPara(@RequestParam(value = "page", defaultValue = "1") int pageNumber,
                               @RequestParam(value = "page.size", defaultValue = PAGE_SIZE) int pageSize,
                               @RequestParam(value = "eventType", defaultValue = "") String eventType, ServletRequest request,
                               @RequestParam(value = "option", defaultValue = "future") String option,
                               @RequestParam(value = "query", defaultValue = "") String query, Model model) {
        System.out.println("Hello");
        Date now = new Date();
        System.out.println(now.toString());
        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "etype_");
        Page<EventInfo> eiPage = eiService.getEventsWithTimeAndType(pageNumber, pageSize, eventType, query, now,
                option);
        System.out.println(eiPage.getTotalPages());
        model.addAttribute("eiPage", eiPage);
        model.addAttribute("query", query);
        model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "etype_"));
        return "event/advancedlist";
    }*/

    /*@RequestMapping(value = "search")
    public String search(@RequestParam(value = "page", defaultValue = "1") int pageNumber,
                         @RequestParam(value = "page.size", defaultValue = PAGE_SIZE) int pageSize,
                         @RequestParam(value = "eventType", defaultValue = "") String eventType, ServletRequest request,
                         @RequestParam(value = "option", defaultValue = "future") String option,
                         @RequestParam(value = "query", defaultValue = "") String query, Model model) {
        System.out.println("Hello");
        Date now = new Date(115, 1, 1);
        System.out.println(now.toString());
        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "setype_");
        Page<EventInfo> eiPage = eiService.getEventsWithTimeAndType(pageNumber, pageSize, eventType, query, now,
                option);
        System.out.println(eiPage.getTotalPages());
        model.addAttribute("eiPage", eiPage);
        model.addAttribute("query", query);
        model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "setype_"));
        return "search/events";
    }*/

    @RequestMapping(value = "detail/{id}", method = RequestMethod.GET)
    public String detail(@PathVariable int id,
                         @RequestParam(value = "page", defaultValue = "1") int pageNumber,
                         @RequestParam(value = "page.size", defaultValue = PAGE_SIZE) int pageSize,
                         ServletRequest request,
                         Model model) {
        EventInfo event = eiService.findEntity(id);
        String pics = event.getEventPic();
        String headPic = "";
        List<String> picList = new ArrayList<String>();
        if (pics != null) {
            String[] array = pics.split(",");
            headPic = array[0];
            for (String str : array) {
                if (!str.trim().equals("")) {
                    picList.add(str);
                }
            }
        }
        String type = event.getEventType();
        if (type == null || type.equals("")) {
            type = "1";
        }
        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "ci_");
        Page<CommentInfo> ciPage = ciService.getEventComments(pageNumber, pageSize, "12", id);
        model.addAttribute("ciPage", ciPage);
        model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "ci_"));

        model.addAttribute("type", EventInfoController.CLASSMAP.get(type));
        model.addAttribute("headPic", headPic);
        model.addAttribute("picList", picList);
        model.addAttribute("event", event);
        model.addAttribute("id", event.getId());

        // 精选活动
        Page<EventInfo> eiPage = eiService.getEventsWithCondition(1, 5, "", "", "0", "", "");
        model.addAttribute("eiPage", eiPage);

        model.addAttribute("today", new Date());

        if (event.getEventType().equals("3")){
            // 线上投票
            String joinUrl = OAuthUtil.getOauthUrl("joinVote:"+id);
            model.addAttribute("joinUrl", joinUrl);
            return "event/voteDetail";
        }else {
            if (!event.getText2().equals("0")){
                // 收费活动
                String joinUrl = OAuthUtil.getOauthUrl("joinPay:"+id);
                // String joinUrl = "http://roger.tunnel.qydev.com/WechatService/testpay/payindex";
                model.addAttribute("joinUrl", joinUrl);
                return "event/notVoteDetail";
            }
            String joinUrl = OAuthUtil.getOauthUrl("joinNotVote:"+id);
            model.addAttribute("joinUrl", joinUrl);
            return "event/notVoteDetail";
        }
    }

    @RequestMapping(value = "/release", method = RequestMethod.GET)
    public String release(){
        return "event/launchActivity";
    }

    @RequestMapping(value = "/release", method = RequestMethod.POST)
    public String release(String activity, String picTag, String voteTag, Model model){
        String baseHref = "";
        if (activity.equals("0")){
            // 线下活动
            baseHref = "releaseOffline";
        }else {
            // 线上活动
            baseHref = "releaseOnline/picTag/voteTag";
            if (picTag!=null){
                baseHref = baseHref.replace("picTag", "1");
            }else {
                baseHref = baseHref.replace("picTag", "0");
            }
            if (voteTag!=null){
                baseHref = baseHref.replace("voteTag", "1");
            }else {
                baseHref = baseHref.replace("voteTag", "0");
            }
        }
        model.addAttribute("baseHref", baseHref);
        return "event/activityTypeSelect";
        /*if (activity.equals("0")){
            // redirectAttributes.addFlashAttribute("activity", "0");
            // 线下活动
            return "redirect:releaseOffline";
        }else {
            // redirectAttributes.addFlashAttribute("activity", "1");
            // redirectAttributes.addFlashAttribute("picTag", picTag);
            // redirectAttributes.addFlashAttribute("voteTag", picTag);
            // 线上活动
            return "redirect:releaseOnline";
        }*/
    }

    /*@RequestMapping(value = "/typeSelect", method = RequestMethod.GET)
    public String typeSelect(){
        return "event/activityTypeSelect";
    }*/

    @RequestMapping(value = "/releaseOffline/{eventCls}", method = RequestMethod.GET)
    public String releaseOffline(@PathVariable Integer eventCls,
                                 Model model){
        model.addAttribute("eventCls", eventCls);
        return "event/launchOfflineActivity";
    }

    @RequestMapping(value = "/releaseOffline", method = RequestMethod.POST)
    public String releaseOffline(WebEventInfo webEventInfo,
                                 HttpServletRequest request,
                                 HttpSession session) throws IOException {
        // System.out.println("to release offline post!"+eventInfo.getEventCls());
        String baseDir = request.getSession().getServletContext().getRealPath("/") + File.separator;
        List<String> eventPicList = new ArrayList<>();
        for (MultipartFile file: webEventInfo.getMultipartFiles()){
            if (!file.isEmpty()){
                String imgPath =  "base" + File.separator + "event"+ File.separator + UUID.randomUUID() + ".jpg";
                eventPicList.add(imgPath);
                FileOutputStream fos = new FileOutputStream(baseDir + imgPath);
                fos.write(file.getBytes());
                fos.close();
            }
        }
        webEventInfo.setEventPic(Joiner.on(",").skipNulls().join(eventPicList));

        WechatUser wechatUser = userService.findWechatUserByOpenId((String) session.getAttribute("openId"));
        APPUser appUser = userService.findAPPUserByAppId(wechatUser.getAppId());

        webEventInfo.setEventCreator(appUser);

        eiService.saveOffline(webEventInfo);
        return "redirect:list";
    }

    @RequestMapping(value = "/releaseOnline/{picTag}/{voteTag}/{eventCls}", method = RequestMethod.GET)
    public String releaseOnline(@PathVariable Integer picTag,
                                @PathVariable Integer voteTag,
                                @PathVariable Integer eventCls,
                                Model model){
        System.out.println(picTag+"\t"+voteTag+"\t"+eventCls);
        model.addAttribute("picTag", picTag);
        model.addAttribute("voteTag", voteTag);
        model.addAttribute("eventCls", eventCls);
        return "event/launchOnlineActivity";
    }

    @RequestMapping(value = "/releaseOnline", method = RequestMethod.POST)
    public String releaseOnline(WebEventInfo webEventInfo,
                                HttpServletRequest request,
                                HttpSession session) throws IOException {
        String baseDir = request.getSession().getServletContext().getRealPath("/") + File.separator;
        List<String> eventPicList = new ArrayList<>();
        for (MultipartFile file: webEventInfo.getMultipartFiles()){
            if (!file.isEmpty()){
                String imgPath =  "base" + File.separator + "event"+ File.separator + UUID.randomUUID() + ".jpg";
                eventPicList.add(imgPath);
                FileOutputStream fos = new FileOutputStream(baseDir + imgPath);
                fos.write(file.getBytes());
                fos.close();
            }
        }
        webEventInfo.setEventPic(Joiner.on(",").skipNulls().join(eventPicList));

        WechatUser wechatUser = userService.findWechatUserByOpenId((String) session.getAttribute("openId"));
        APPUser appUser = userService.findAPPUserByAppId(wechatUser.getAppId());

        webEventInfo.setEventCreator(appUser);

        eiService.saveOnline(webEventInfo);
        return "redirect:list";
    }

    /**
     * 判断 用户 是否可以报名活动(不能重复报名)
     * @param id 活动event id
     * @param session
     * @return
     */
    @RequestMapping(value = "/checkJoin/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> checkJoin(@PathVariable Integer id,
                                         HttpSession session){
        Map<String, Object> map = new HashMap<>();
        WechatUser wechatUser = userService.findWechatUserByOpenId((String) session.getAttribute("openId"));
        APPUser appUser = userService.findAPPUserByAppId(wechatUser.getAppId());
        if (eventSignService.findByEventIdAndEventSignCreator(id, appUser)==null){
            map.put("success", true);
        }else {
            map.put("success", false);
        }
        return map;
    }

    /**
     * 参加非投票活动报名
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/joinNotVote/{id}", method = RequestMethod.GET)
    public String joinNotVoteActivity(String openId,
                                      HttpSession session,
                                      @PathVariable Integer id,
                                      Model model){
        if (openId != null){
            session.setAttribute("openId", openId);
        }
        model.addAttribute("eventId", id);
        return "event/joinNotVoteActivity";
    }

    @RequestMapping(value = "/joinNotVote/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> joinNotVoteActivity(@PathVariable Integer id,
                                    String mobile,
                                    HttpSession session){
        Map<String, String> map = new HashMap<>();

        WechatUser wechatUser = userService.findWechatUserByOpenId((String) session.getAttribute("openId"));
        APPUser appUser = userService.findAPPUserByAppId(wechatUser.getAppId());
        if (eventSignService.findByEventIdAndEventSignCreator(id, appUser)==null){
            EventSign eventSign = new EventSign();
            eventSign.setEventId(id);
            // eventSign.setEventSignPic();
            eventSign.setEventSignCreator(appUser);
            eventSign.setEventSignCreatetime(new Date());
            // 可用
            eventSign.setEventSignStatus("1");
            // 审核中
            eventSign.setEventSignProcess("2");
            eventSign.setEventSignPraise("0");
            eventSign.setEventUpdatetime(new Date());
            eventSign.setEventMobile(mobile);
            eventSign.setEventSignPic("");

            eventSignService.save(eventSign);
            map.put("msg", "感谢您的报名，请等待审核！");
        }else {
            map.put("msg", "请勿重复报名!");
        }

        return map;
    }

    @RequestMapping(value = "/joinPay/{id}", method = RequestMethod.GET)
    public String joinPayActivity(String openId,
                                      HttpSession session,
                                      @PathVariable Integer id,
                                      Model model){
        if (session.getAttribute("openId") != null){
            openId = (String) session.getAttribute("openId");
        }else {
            if (openId != null){
                session.setAttribute("openId", openId);
            }
        }
        WechatUser wechatUser = userService.findWechatUserByOpenId(openId);
        APPUser appUser = userService.findAPPUserByAppId(wechatUser.getAppId());
        EventInfo event = eiService.findEntity(id);
        model.addAttribute("fee", event.getText2());
        model.addAttribute("username", appUser.getUserName());
        model.addAttribute("eventId", id);
        return "event/joinPayActivity";
    }

    @RequestMapping(value = "/joinPay/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> joinPayActivity(@PathVariable Integer id,
                                               String mobile,
                                               HttpSession session){
        Map<String, String> map = new HashMap<>();

        WechatUser wechatUser = userService.findWechatUserByOpenId((String) session.getAttribute("openId"));
        APPUser appUser = userService.findAPPUserByAppId(wechatUser.getAppId());
        if (eventSignService.findByEventIdAndEventSignCreator(id, appUser)==null){
            EventSign eventSign = new EventSign();
            eventSign.setEventId(id);
            // eventSign.setEventSignPic();
            eventSign.setEventSignCreator(appUser);
            eventSign.setEventSignCreatetime(new Date());
            // 可用
            eventSign.setEventSignStatus("1");
            // 审核中
            eventSign.setEventSignProcess("1");
            eventSign.setEventSignPraise("0");
            eventSign.setEventUpdatetime(new Date());
            eventSign.setEventMobile(mobile);
            eventSign.setEventSignPic("");

            eventSignService.save(eventSign);
            map.put("msg", "恭喜您, 报名成功！");
        }else {
            map.put("msg", "请勿重复报名!");
        }

        return map;
    }

    /**
     * 参加投票活动报名
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/joinVote/{id}", method = RequestMethod.GET)
    public String joinVoteActivity(String openId,
                                   HttpSession session,
                                   @PathVariable Integer id,
                                   Model model){
        if (openId != null){
            session.setAttribute("openId", openId);
        }
        model.addAttribute("eventId", id);
        return "event/joinVoteActivity";
    }

    @RequestMapping(value = "/joinVote/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> joinVoteActivity(@PathVariable Integer id,
                                   String mobile,
                                   MultipartFile file,
                                   HttpServletRequest request,
                                   HttpSession session) throws IOException {
        Map<String, String> map = new HashMap<>();
        WechatUser wechatUser = userService.findWechatUserByOpenId((String) session.getAttribute("openId"));
        APPUser appUser = userService.findAPPUserByAppId(wechatUser.getAppId());
        if (eventSignService.findByEventIdAndEventSignCreator(id, appUser)==null){
            String baseDir = request.getSession().getServletContext().getRealPath("/") + File.separator;
            String imgPath = "";
            if (!file.isEmpty()){
                imgPath =  "base" + File.separator + "sign"+ File.separator + UUID.randomUUID() + ".jpg";
                FileOutputStream fos = new FileOutputStream(baseDir + imgPath);
                fos.write(file.getBytes());
                fos.close();
            }
            System.out.println("imgPath: "+imgPath);

            EventSign eventSign = new EventSign();
            eventSign.setEventId(id);
            // eventSign.setEventSignPic();
            eventSign.setEventSignCreator(appUser);
            eventSign.setEventSignCreatetime(new Date());
            // 可用
            eventSign.setEventSignStatus("1");
            // 审核中
            eventSign.setEventSignProcess("2");
            eventSign.setEventSignPraise("0");
            eventSign.setEventUpdatetime(new Date());
            eventSign.setEventMobile(mobile);
            eventSign.setEventSignPic(imgPath);

            eventSignService.save(eventSign);

            map.put("msg", "感谢您的报名，请等待审核！");
        }else {
            map.put("msg", "请勿重复报名!");
        }
        return map;
    }

    /**
     * 访问voteIndex
     *
     * @param id eventInfo.id
     * @param model
     * @return
     */
    @RequestMapping(value = "/vote/{id}", method = RequestMethod.GET)
    public String vote(@PathVariable Integer id,
                       Model model,
                       HttpSession session){
        WechatUser wechatUser = userService.findWechatUserByOpenId((String) session.getAttribute("openId"));
        APPUser appUser = userService.findAPPUserByAppId(wechatUser.getAppId());
        List<EventVote> eventVoteList = eventVoteService.getEventVoteListByVoteCreator(appUser);
        Set<EventSign> eventSignRecordList = new TreeSet<>(new EventSignComparator());
        for (EventVote eventVote: eventVoteList){
            eventSignRecordList.add(eventSignService.findByEventIdAndEventSignCreator(eventVote.getVoteEventId(), eventVote.getVoteUser()));
        }

        List<EventSign> eventSignList = eventSignService.getEventSignToVoteList(id);
        List<EventSign> eventSignRankList = eventSignService.getEventSignToVoteRankList(id);
        model.addAttribute("eventSignList", eventSignList);
        model.addAttribute("eventSignRankList", eventSignRankList);
        model.addAttribute("eventSignRecordList", eventSignRecordList);
        model.addAttribute("eventId", id);
        return "event/voteIndex";
    }

    /**
     * 提交投票
     *
     * @param eventId
     * @param eventSignId
     * @param session
     * @return
     */
    @RequestMapping(value = "/vote/{eventId}/{eventSignId}", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> vote(@PathVariable Integer eventId,
                                    @PathVariable Integer eventSignId,
                                    HttpSession session){
        Map<String, String> map = new HashMap<>();
        WechatUser wechatUser = userService.findWechatUserByOpenId((String) session.getAttribute("openId"));
        APPUser appUser = userService.findAPPUserByAppId(wechatUser.getAppId());
        System.out.println(eventId+"\t"+appUser.getId());
        if (eventVoteService.checkVote(eventId, appUser)){
            EventVote eventVote = new EventVote();
            eventVote.setVoteCreator(appUser);
            eventVote.setVoteUser(eventSignService.findEntity(eventSignId).getEventSignCreator());
            eventVote.setVoteEventId(eventId);
            eventVote.setVoteDate(new Date());
            eventVoteService.save(eventVote);

            EventSign eventSign = eventSignService.findEntity(eventSignId);
            // 点赞后 数量+1
            String praiseCount = String.valueOf(Integer.parseInt(eventSign.getEventSignPraise())+1);
            eventSignService.increasePraiseCount(praiseCount, eventSignId);
            map.put("msg", "感谢您的投票！");
            map.put("eventSignId", String.valueOf(eventSignId));
            map.put("praiseCount", praiseCount);
        }else {
            map.put("msg", "您今日已经送出宝贵的一票，请明日再来哦！");
        }

        return map;
    }

   /* @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String search(@RequestParam(value = "page", defaultValue = "1") int pageNumber,
                         @RequestParam(value = "page.size", defaultValue = PAGE_SIZE) int pageSize,
                         String query,
                         Model model){
        Page<EventInfo> eiPage = eiService.search(pageNumber, pageSize, query);
        model.addAttribute("eiPage", eiPage);
        model.addAttribute("query", query);
        return "event/eventlist";
    }*/

    /**
     * Ajax, 对活动, 发表评论
     */
    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> comment(CommentInfo commentInfo, HttpSession session){
        Map<String, String> map = new HashMap<>();
        String openId = (String) session.getAttribute("openId");
        System.out.println("comment:" + openId);
        WechatUser wechatUser = userService.findWechatUserByOpenId(openId);
        if (wechatUser == null){
            // 用户还未注册
            map.put("flag", "-1");
            return map;
        }
        APPUser appUser = userService.findAPPUserByAppId(wechatUser.getAppId());

        commentInfo.setCommentReplyId(0);
        // "1":评论
        commentInfo.setCommentType("1");
        // "12":活动模块
        commentInfo.setCommentMode("12");
        commentInfo.setCommentCreator(appUser);
        commentInfo.setCommentCreatetime(new Date());
        // "1":可用
        commentInfo.setCommentStatus("1");

        ciService.save(commentInfo);

        eiService.increaseCommentCount(commentInfo.getInfoId());
        System.out.println("event comment: id="+commentInfo.getInfoId());
        map.put("flag", "1");
        return map;
    }
}
