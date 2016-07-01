package edu.whu.irlab.client.service;

import edu.whu.irlab.client.entity.APPUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by Roger on 2016/6/12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext.xml"})
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void findFriend() throws Exception {
        APPUser appUser = new APPUser();
        appUser.setUserAgeDesc("青葱年华 18~23");
        // appUser.setUserStar("狮子座");
        // appUser.setUserArea("武昌区");
        // appUser.setUserCareer("采购");
        // appUser.setText4("");

        Page<APPUser> page = userService.findFriend(1, 10, appUser);
        System.out.println(page.getContent().size());
    }

}