package edu.whu.irlab.client.service;

import edu.whu.irlab.client.entity.APPUser;
import edu.whu.irlab.client.entity.APPUserFriend;
import edu.whu.irlab.client.entity.BaseUser;
import edu.whu.irlab.client.entity.WechatUser;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

/**
 * Created by Roger on 2016/5/22.
 */
 public interface UserService {

    //  APPUser findById(Integer id);

    //  APPUser findByUsername(String username);

     APPUser findAPPUserByMobile(String mobile);

     APPUser findAPPUserByAppId(Integer appId);

     void registerUser(BaseUser user);

     Map<String, Object> loginUser(BaseUser user);

     WechatUser findWechatUserByMobile(String mobile);

     WechatUser findWechatUserByOpenId(String openId);

     int completeInfo(APPUser appUser);

     int updateUserPhoto(String userPhoto, Integer appId);

     List<APPUser> findFriend(APPUser appUser);

     Page<APPUser> findFriend(int pageNumber, int pageSize, APPUser appUser);

     Page<APPUserFriend> getMyFriend(int pageNumber, int pageSize, APPUser appUser);

     int updateUsername(APPUser appUser);

    void findPsd(BaseUser baseUser);
}
