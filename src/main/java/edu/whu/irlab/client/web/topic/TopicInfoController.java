package edu.whu.irlab.client.web.topic;

import com.google.common.base.Joiner;
import com.google.common.collect.Maps;
import edu.whu.irlab.client.entity.*;
import edu.whu.irlab.client.service.BaseClsService;
import edu.whu.irlab.client.service.CommentInfoService;
import edu.whu.irlab.client.service.TopicInfoService;
import edu.whu.irlab.client.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
 * Created by Roger on 2016/5/22.
 */
@Controller
@RequestMapping("/topic")
public class TopicInfoController {

    private static final String PAGE_SIZE = "10";

    private static Map<String, String> sortTypes = Maps.newLinkedHashMap();

    static {
        sortTypes.put("auto", "自动");
        sortTypes.put("title", "标题");
    }

    @Autowired
    private BaseClsService bcService;

    @Autowired
    private TopicInfoService tiService;

    @Autowired
    private CommentInfoService ciService;

    @Autowired
    private UserService userService;

    /**
     * 话题详情页
     *
     * @param id
     * @param pageNumber
     * @param pageSize
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/detail")
    public String detail(@RequestParam(value = "id") int id,
                         @RequestParam(value = "page", defaultValue = "1") int pageNumber,
                         @RequestParam(value = "page.size", defaultValue = PAGE_SIZE) int pageSize, ServletRequest request,
                         Model model) {
        TopicInfo topic = tiService.findEntity(id);
        String pics = topic.getTopicPic();
        List<String> picList = new ArrayList<String>();
        if (pics != null) {
            String[] array = pics.split(",");
            for (String str : array) {
                if (!str.trim().equals("")) {
                    picList.add(str);
                }
            }
        }

        String text4Pics = topic.getText4();
        List<String> text4PicList = new ArrayList<String>();
        if (text4Pics != null) {
            String[] array = text4Pics.split(",");
            for (String str : array) {
                if (!str.trim().equals("")) {
                    text4PicList.add(str);
                }
            }
        }

        // Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "tci_");
        Page<CommentInfo> ciPage = ciService.getEventComments(pageNumber, pageSize, "11", id);
        model.addAttribute("ciPage", ciPage);
        model.addAttribute("picList", picList);
        model.addAttribute("text4PicList", text4PicList);
        model.addAttribute("topicInfo", topic);
        model.addAttribute("id", topic.getId());
        // model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "tci_"));
        // List<BaseCls> clss = bcService.findAllClassesAsList();
        // for (BaseCls bc : clss) {
        //     Page<TopicInfo> tiPage = tiService.findTopTopicInfosWithClass(bc.getId(), 1, 3);
        //     model.addAttribute("item" + bc.getId(), tiPage.getContent());
        // }
        model.addAttribute("topicCls", topic.getTopicCls());
        model.addAttribute("topicCreator", topic.getTopicCreator());
        return "topic/topicdetail";
    }

    /**
     * 话题列表页, 通过OAuthController进入后, 将openId放入session中
     *
     * @param pageNumber
     * @param pageSize
     * @param model
     * @return
     */
    @RequestMapping(value = "/list")
    public String listWithPara(@RequestParam(value = "page", defaultValue = "1") int pageNumber,
                               @RequestParam(value = "page.size", defaultValue = PAGE_SIZE) int pageSize,
                               Model model) {
        Page<TopicInfo> tiPage = tiService.getList(pageNumber, pageSize);
        model.addAttribute("tiPage", tiPage);
        model.addAttribute("page", pageNumber);
        return "topic/topiclist";
    }

    @RequestMapping(value = "/loadList", method = RequestMethod.POST)
    @ResponseBody
    public List<TopicInfo> loadList(@RequestParam(value = "page", defaultValue = "1") int pageNumber,
                                    @RequestParam(value = "page.size", defaultValue = PAGE_SIZE) int pageSize){
        System.out.println("Ajax load topic list---"+pageNumber);
        Page<TopicInfo> tiPage = tiService.getList(pageNumber, pageSize);
        return tiPage.getContent();
    }

    /**
     * Ajax, 对话题, 发表评论
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
        // "11":话题模块
        commentInfo.setCommentMode("11");
        commentInfo.setCommentCreator(appUser);
        commentInfo.setCommentCreatetime(new Date());
        // "1":可用
        commentInfo.setCommentStatus("1");

        ciService.save(commentInfo);


        TopicInfo topicInfo = tiService.findEntity(commentInfo.getInfoId());
        int commentCount = Integer.parseInt(topicInfo.getTopicCommentCount()) + 1;
        tiService.increaseCommentCount(String.valueOf(commentCount), topicInfo.getId());
        map.put("flag", "1");
        return map;
    }

    @RequestMapping(value = "/release", method = RequestMethod.GET)
    public String release(){
        return "topic/topic_release";
    }

    @RequestMapping(value = "/release", method = RequestMethod.POST)
    public String release(BaseTopicInfo baseTopicInfo,
                          HttpServletRequest request,
                          HttpSession session) throws IOException {
        String baseDir = request.getSession().getServletContext().getRealPath("/") + File.separator;
        System.out.println(baseTopicInfo.getTopicClsId()+"\t"+baseTopicInfo.getTopicContent()+"\t"+baseTopicInfo.getMultipartFiles().size());
        List<String> topicPic = new ArrayList<>();
        for (MultipartFile file: baseTopicInfo.getMultipartFiles()){
            if (!file.isEmpty()){
                String imgPath =  "base" + File.separator + "sns"+ File.separator + UUID.randomUUID() + ".jpg";
                topicPic.add(imgPath);
                FileOutputStream fos = new FileOutputStream(baseDir + imgPath);
                fos.write(file.getBytes());
                fos.close();
            }
        }
        WechatUser wechatUser = userService.findWechatUserByOpenId((String) session.getAttribute("openId"));
        APPUser appUser = userService.findAPPUserByAppId(wechatUser.getAppId());
        TopicInfo topicInfo = new TopicInfo();
        topicInfo.setTopicCls(bcService.findById(baseTopicInfo.getTopicClsId()));
        topicInfo.setTopicContent(baseTopicInfo.getTopicContent());
        topicInfo.setTopicCreator(appUser);
        topicInfo.setTopicCreatetime(new Date());
        topicInfo.setTopicStatus("1");
        topicInfo.setTopicCommentCount("0");
        topicInfo.setTopicPraiseCount("0");
        topicInfo.setTopicForwardCount("0");
        // 图片保存位置, 为了和app的保存属性topicPic区分开
        topicInfo.setText4(Joiner.on(",").skipNulls().join(topicPic));

        tiService.save(topicInfo);

        return "redirect: list";
    }

    @RequestMapping(value = "/praise/{infoId}", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> praise(@PathVariable Integer infoId, HttpSession session){
        Map<String, String> map = new HashMap<>();
        String openId = (String) session.getAttribute("openId");
        System.out.println("praise:" + openId);
        WechatUser wechatUser = userService.findWechatUserByOpenId(openId);
        if (wechatUser == null){
            // 用户还未注册
            map.put("flag", "-1");
            return map;
        }
        APPUser appUser = userService.findAPPUserByAppId(wechatUser.getAppId());

        CommentInfo commentInfo = ciService.findByCommentCreatorAndInfoIdAndCommentType(appUser, infoId, "2");
        // 不能重复点赞
        if (commentInfo != null){
            System.out.println("重复点赞!");
            map.put("flag", "0");
            return map;
        }

        commentInfo = new CommentInfo();
        System.out.println("点赞!");
        // "2":点赞
        commentInfo.setCommentType("2");
        commentInfo.setCommentMode("11");
        commentInfo.setCommentCreator(appUser);
        commentInfo.setCommentCreatetime(new Date());
        commentInfo.setInfoId(infoId);
        commentInfo.setCommentContent("");
        // "1":可用
        commentInfo.setCommentStatus("1");

        ciService.save(commentInfo);

        TopicInfo topicInfo = tiService.findEntity(infoId);
        // 点赞后 数量+1
        int praiseCount = Integer.parseInt(topicInfo.getTopicPraiseCount())+1;
        topicInfo.setTopicPraiseCount(String.valueOf(praiseCount));
        // 更新topic的点赞数
        tiService.increasePraiseCount(topicInfo.getTopicPraiseCount(), infoId);

        map.put("flag", "1");
        map.put("praiseCount", topicInfo.getTopicPraiseCount());
        return map;
    }





    /**
     * 对话题, 发表评论
     */
    /*@RequestMapping(value = "comment2", method = RequestMethod.POST)
    public String comment2(CommentInfo commentInfo, HttpSession session){
        String openId = (String) session.getAttribute("openId");
        System.out.println("comment:" + openId);
        // String openId = "oWuwPuEpIhZcy_K9z7hkiWqJYnKA";
        WechatUser wechatUser = userService.findWechatUserByOpenId(openId);
        APPUser appUser = userService.findAPPUserByAppId(wechatUser.getAppId());

        commentInfo.setCommentReplyId(0);
        // "1":评论
        commentInfo.setCommentType("1");
        // "11":话题模块
        commentInfo.setCommentMode("11");
        commentInfo.setCommentCreator(appUser);
        commentInfo.setCommentCreatetime(new Date());
        // "1":可用
        commentInfo.setCommentStatus("1");

        ciService.save(commentInfo);
        return "redirect:detail";
    }*/

    @RequestMapping(value = "search")
    public String search(@RequestParam(value = "page", defaultValue = "1") int pageNumber,
                         @RequestParam(value = "page.size", defaultValue = PAGE_SIZE) int pageSize,
                         @RequestParam(value = "query", defaultValue = "") String query, ServletRequest request, Model model) {
        System.out.println("--------------------");
        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "stitype_");
        Page<TopicInfo> tiPage = tiService.findByTitle(pageNumber, pageSize, query);
        model.addAttribute("tiPage", tiPage);
        model.addAttribute("query", query);
        model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "stitype_"));
        return "search/topics";
    }

    @RequestMapping(value = "entry")
    public String storesLists(Model model) {
        List<BaseCls> clss = bcService.findAllClassesAsList();
        for (BaseCls bc : clss) {
            Page<TopicInfo> tiPage = tiService.findTopTopicInfosWithClass(bc.getId(), 1, 6);
            if (tiPage.getContent().size() != 6)
                continue;
            List<TopicInfo> list = new ArrayList<TopicInfo>();
            for (int i = 3; i < 6; i++) {
                list.add(tiPage.getContent().get(i));
            }
            model.addAttribute("top1" + bc.getId(), tiPage.getContent().get(1));
            model.addAttribute("top2" + bc.getId(), tiPage.getContent().get(2));
            model.addAttribute("top3" + bc.getId(), tiPage.getContent().get(3));
            model.addAttribute("item" + bc.getId(), list);
        }
        return "topic/topicentry";
    }
}
