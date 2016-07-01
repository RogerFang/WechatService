package edu.whu.irlab.client.repository;

import edu.whu.irlab.client.entity.APPUser;
import edu.whu.irlab.client.entity.CommentInfo;
import edu.whu.irlab.client.service.CommentInfoService;
import edu.whu.irlab.client.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by Roger on 2016/5/27.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext.xml"})
public class CommentInfoDaoTest {

    @Autowired
    private UserService userService;

    @Autowired
    private CommentInfoService commentInfoService;

    @Test
    public void testFindByCommentCreatorAndInfoId(){
        APPUser appUser = userService.findAPPUserByAppId(31235248);
        CommentInfo commentInfo = commentInfoService.findByCommentCreatorAndInfoIdAndCommentType(appUser, 4040, "2");
        Assert.assertEquals(commentInfo, null);
    }
}