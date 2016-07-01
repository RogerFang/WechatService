package edu.whu.irlab.client.repository;

import edu.whu.irlab.client.entity.EventInfo;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Roger on 2016/6/7.
 */
public interface EventInfoDao
        extends PagingAndSortingRepository<EventInfo, Integer>, JpaSpecificationExecutor<EventInfo> {

    //
    // @Modifying
    // @Query("delete from Task task where task.user.userID=?1")
    // void deleteByUserId(Integer userID);

    @Transactional
    @Modifying
    @Query("update EventInfo ei set ei.eventCommentCount = ?1 where ei.id = ?2")
    int increaseCommentCount(String commentCount, Integer id);
}
