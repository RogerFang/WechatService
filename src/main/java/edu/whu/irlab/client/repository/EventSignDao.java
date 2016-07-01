package edu.whu.irlab.client.repository;

import edu.whu.irlab.client.entity.APPUser;
import edu.whu.irlab.client.entity.EventSign;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Roger on 2016/6/16.
 */
public interface EventSignDao
        extends PagingAndSortingRepository<EventSign, Integer>, JpaSpecificationExecutor<EventSign> {

    EventSign findByEventIdAndEventSignCreator(Integer eventId, APPUser eventSignCreator);

    @Transactional
    @Modifying
    @Query("update EventSign es set es.eventSignPraise = ?1 where es.eventSignId = ?2")
    int increasePraiseCount(String eventSignPraise, Integer id);
}
