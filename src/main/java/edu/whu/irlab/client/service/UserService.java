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

    // public APPUser findById(Integer id);

    // public APPUser findByUsername(String username);

    public APPUser findAPPUserByMobile(String mobile);

    public APPUser findAPPUserByAppId(Integer appId);

    public void registerUser(BaseUser user);

    public Map<String, Object> loginUser(BaseUser user);

    public WechatUser findWechatUserByMobile(String mobile);

    public WechatUser findWechatUserByOpenId(String openId);

    public int completeInfo(APPUser appUser);

    public int updateUserPhoto(String userPhoto, Integer appId);

    public List<APPUser> findFriend(APPUser appUser);

    public Page<APPUser> findFriend(int pageNumber, int pageSize, APPUser appUser);

    public Page<APPUserFriend> getMyFriend(int pageNumber, int pageSize, APPUser appUser);

    public int updateUsername(APPUser appUser);
}
