package edu.whu.irlab.client.repository;

import edu.whu.irlab.client.entity.APPUserFriend;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by Roger on 2016/6/23.
 */
public interface APPUserFriendDao
        extends PagingAndSortingRepository<APPUserFriend, Integer>, JpaSpecificationExecutor<APPUserFriend> {
}
