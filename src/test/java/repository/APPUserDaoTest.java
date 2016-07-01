package repository;

import edu.whu.irlab.client.entity.APPUser;
import edu.whu.irlab.client.repository.APPUserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Roger on 2016/5/22.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext.xml"})
// extends SpringTransactionalTestCase, 继承这个使用spring-test进行测试，默认会回滚事务
public class APPUserDaoTest{

    @Autowired
    private APPUserDao appUserDao;

    @Test
    public void findByMobile(){
        APPUser appUser = appUserDao.findByMobileAndEnabled("13237120983", 1);
        System.out.println(appUser.getUserName());
    }

    @Test
    public void findByUserCode(){
        APPUser appUser = new APPUser();
        appUser.setUserCode("11111111111");
        appUser.setUserName("fanglong");
        appUser.setPassword("123456");
        appUser.setMobile("2221");
        appUser.setEnabled(1);
        appUser.setRoleID(4);
        // APPUser appUser = appUserDao.findByUserCode("13237120983");
        // APPUser appUser = appUserDao.findOne(31235239);
        appUser = appUserDao.save(appUser);
        System.out.println(appUser.getId());

        // appUserDao.delete(31235240);
        // System.out.println(appUser.getUserName());
    }

}
