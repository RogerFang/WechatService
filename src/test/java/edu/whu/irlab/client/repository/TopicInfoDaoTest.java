package edu.whu.irlab.client.repository;

import edu.whu.irlab.client.entity.APPUser;
import edu.whu.irlab.client.entity.TopicInfo;
import edu.whu.irlab.client.entity.WechatUser;
import edu.whu.irlab.client.service.TopicInfoService;
import edu.whu.irlab.client.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Roger on 2016/5/27.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext.xml"})
public class TopicInfoDaoTest {

    @Autowired
    private TopicInfoDao topicInfoDao;

    @Autowired
    private UserService userService;

    @Test
    public void testIncreasePraise(){
        topicInfoDao.increasePraiseCount("100", 4040);
    }

    @Test
    public void testFindByTopicCreator(){
        WechatUser wechatUser = userService.findWechatUserByOpenId("oWuwPuEpIhZcy_K9z7hkiWqJYnKA");
        APPUser appUser = userService.findAPPUserByAppId(31235248);
        List<TopicInfo> topicInfos = topicInfoDao.findByTopicCreator(appUser);
        System.out.println(topicInfos.size());
    }

    @Test
    public void testIncreaseComment(){
        topicInfoDao.increaseCommentCount("1", 4043);
    }
}