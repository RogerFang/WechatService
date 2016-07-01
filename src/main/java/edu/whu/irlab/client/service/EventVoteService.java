package edu.whu.irlab.client.service;

import edu.whu.irlab.client.entity.APPUser;
import edu.whu.irlab.client.entity.EventVote;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by Roger on 2016/6/16.
 */
public interface EventVoteService {

    public EventVote save(EventVote eventVote);

    public boolean checkVote(Integer eventId, APPUser voteCreator);

    public List<EventVote> getEventVoteListByVoteCreator(APPUser voteCreator);


}
