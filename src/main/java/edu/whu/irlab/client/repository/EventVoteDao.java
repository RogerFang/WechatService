package edu.whu.irlab.client.repository;

import edu.whu.irlab.client.entity.EventVote;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by Roger on 2016/6/16.
 */
public interface EventVoteDao
        extends PagingAndSortingRepository<EventVote, Integer>, JpaSpecificationExecutor<EventVote> {

}
