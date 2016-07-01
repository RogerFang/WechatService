package edu.whu.irlab.client.repository;

import edu.whu.irlab.client.entity.WechatUser;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by Roger on 2016/5/22.
 */
public interface WechatUserDao extends PagingAndSortingRepository<WechatUser, Integer> {
    WechatUser findByOpenId(String openId);

    WechatUser findByMobile(String mobile);
}
