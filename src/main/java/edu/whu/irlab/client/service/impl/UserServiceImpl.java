package edu.whu.irlab.client.service.impl;

import com.google.common.collect.Maps;
import edu.whu.irlab.client.entity.*;
import edu.whu.irlab.client.repository.APPUserDao;
import edu.whu.irlab.client.repository.APPUserFriendDao;
import edu.whu.irlab.client.repository.WechatUserDao;
import edu.whu.irlab.client.service.UserService;
import edu.whu.irlab.client.util.CDynamicSpecifications;
import edu.whu.irlab.client.util.CSearchFilter;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springside.modules.persistence.DynamicSpecifications;
import org.springside.modules.persistence.SearchFilter;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Roger on 2016/5/22.
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private APPUserDao appUserDao;

    @Autowired
    private WechatUserDao wechatUserDao;

    @Autowired
    private APPUserFriendDao appUserFriendDao;

    @Override
    public APPUser findAPPUserByMobile(String mobile) {
        return appUserDao.findByMobileAndEnabled(mobile, 1);
    }

    @Override
    public APPUser findAPPUserByAppId(Integer appId) {
        return appUserDao.findOne(appId);
    }

    @Override
    public void registerUser(BaseUser user) {
        String mobile = user.getMobile();
        APPUser appUser = appUserDao.findByMobileAndEnabled(mobile, 1);
        WechatUser wechatUser = wechatUserDao.findByMobile(mobile);
        if (wechatUser!=null){
            System.out.println("WechatUser: 该用户已注册");
            return;
        }

        if (appUser==null){
            appUser = new APPUser();
            appUser.setMobile(mobile);
            appUser.setPassword(user.getPassword());
            appUser.setUserCode(mobile);
            appUser.setEnabled(1);
            appUser.setRoleID(4);
            appUser.setUserLevel("1");
            appUser.setCreateTime(new Date());
            appUser.setPinknetCoin(100);
            appUser.setUserAgeDesc(user.getUserAgeDesc().substring(0, 4));

            entryptPassword(appUser);
            appUser = appUserDao.save(appUser);

            System.out.println("APPuser: 注册该用户");
        }
        wechatUser = new WechatUser();
        wechatUser.setOpenId(user.getOpenId());
        wechatUser.setAppId(appUser.getId());
        wechatUser.setMobile(mobile);
        wechatUser.setCreateTime(new Date());
        wechatUserDao.save(wechatUser);
        System.out.println("WechatUser: 注册该用户");
    }

    @Override
    public Map<String, Object> loginUser(BaseUser user) {
        Map<String, Object> map = new HashMap<>();
        String mobile = user.getMobile();
        APPUser appUser = appUserDao.findByMobileAndEnabled(mobile, 1);
        WechatUser wechatUser = wechatUserDao.findByMobile(mobile);
        if (wechatUser!=null){
            System.out.println("WechatUser: 该用户已绑定");
            map.put("success", Boolean.FALSE);
            map.put("msg", "该用户已绑定微信");
            return map;
        }
        if (appUser==null){
            System.out.println("AppUser: 该用户还未注册");
            map.put("success", Boolean.FALSE);
            map.put("msg", "请您先注册");
            return map;
        }

        if (appUser.getPassword().equals(entryptPassword(user.getPassword()))){
            wechatUser = new WechatUser();
            wechatUser.setOpenId(user.getOpenId());
            wechatUser.setAppId(appUser.getId());
            wechatUser.setMobile(mobile);
            wechatUser.setCreateTime(new Date());
            wechatUserDao.save(wechatUser);
            System.out.println("WechatUser: 绑定该用户");
            map.put("success", Boolean.TRUE);
            map.put("msg", "成功绑定微信");
            return map;
        }else {
            map.put("success", Boolean.FALSE);
            map.put("msg", "用户名/密码错误");
            return map;
        }
    }

    @Override
    public WechatUser findWechatUserByMobile(String mobile) {
        return wechatUserDao.findByMobile(mobile);
    }

    @Override
    public WechatUser findWechatUserByOpenId(String openId) {
        return wechatUserDao.findByOpenId(openId);
    }

    private void entryptPassword(APPUser user){
        String hashPassword = new Md5Hash(user.getPassword()).toString().toUpperCase();
        user.setPassword(hashPassword);
    }

    private String entryptPassword(String password){
        String hashPassword = new Md5Hash(password).toString().toUpperCase();
        // System.out.println(hashPassword);
        return hashPassword;
    }

    @Override
    public int completeInfo(APPUser appUser) {
        return appUserDao.completeInfo(
                appUser.getUserAgeDesc().substring(0, 4),
                appUser.getUserStar(),
                appUser.getUserArea(),
                appUser.getUserCareer(),
                appUser.getText4(),
                appUser.getId());
    }

    @Override
    public int updateUserPhoto(String userPhoto, Integer appId) {
        return appUserDao.updateUserPhoto(userPhoto, appId);
    }

    @Override
    public List<APPUser> findFriend(APPUser appUser) {
        if (appUser.getUserAgeDesc().length() > 4){
            return appUserDao.findFriend(appUser.getUserAgeDesc().substring(0, 4),
                    appUser.getUserStar(),
                    appUser.getUserArea(),
                    appUser.getUserCareer(),
                    appUser.getText4());
        }else {
            return appUserDao.findFriend(appUser.getUserAgeDesc(),
                    appUser.getUserStar(),
                    appUser.getUserArea(),
                    appUser.getUserCareer(),
                    appUser.getText4());
        }
    }

    @Override
    public Page<APPUser> findFriend(int pageNumber, int pageSize, APPUser appUser) {
        String userAgeDesc = appUser.getUserAgeDesc();
        if (userAgeDesc.length() > 4){
            appUser.setUserAgeDesc(userAgeDesc.substring(0,4));
        }

        Map<String, CSearchFilter> filters = Maps.newHashMap();
        filters.put("userAgeDesc", new CSearchFilter("userAgeDesc", CSearchFilter.COperator.EQ, appUser.getUserAgeDesc()));
        filters.put("userStar", new CSearchFilter("userStar", CSearchFilter.COperator.EQ, appUser.getUserStar()));
        filters.put("userArea", new CSearchFilter("userArea", CSearchFilter.COperator.EQ, appUser.getUserArea()));
        filters.put("userCareer", new CSearchFilter("userCareer", CSearchFilter.COperator.EQ, appUser.getUserCareer()));
        filters.put("text4", new CSearchFilter("text4", CSearchFilter.COperator.EQ, appUser.getText4()));
        Specification<APPUser> spec = CDynamicSpecifications.bySearchFilterOr(filters.values(), APPUser.class);
        PageRequest pageRequest = new PageRequest(pageNumber - 1, pageSize);
        return appUserDao.findAll(spec, pageRequest);
    }

    @Override
    public Page<APPUserFriend> getMyFriend(int pageNumber, int pageSize, APPUser appUser) {
        Sort sort = new Sort(Sort.Direction.DESC, "fCreatetime");
        Map<String, CSearchFilter> filters = Maps.newHashMap();
        filters.put("user", new CSearchFilter("user", CSearchFilter.COperator.EQ, appUser));
        Specification<APPUserFriend> spec = CDynamicSpecifications.bySearchFilter(filters.values(), APPUserFriend.class);
        PageRequest pageRequest = new PageRequest(pageNumber - 1, pageSize, sort);
        return appUserFriendDao.findAll(spec, pageRequest);
    }

    @Override
    public int updateUsername(APPUser appUser) {
        return appUserDao.updateUsername(appUser.getUserName(), appUser.getId());
    }
}
