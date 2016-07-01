package edu.whu.irlab.client.repository;

import edu.whu.irlab.client.entity.APPUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Roger on 2016/6/12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext.xml"})
public class APPUserDaoTest {
    @Autowired
    private APPUserDao appUserDao;

    @Test
    public void findFriend() throws Exception {
        List<APPUser> list = appUserDao.findFriend("青葱岁月","水瓶座", "", "", "");
        System.out.println("size:"+list.size());
        System.out.println(list.get(0).getId());
    }

}