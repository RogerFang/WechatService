package edu.whu.irlab.client.service;

import edu.whu.irlab.client.entity.APPUser;
import edu.whu.irlab.client.entity.EventSign;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by Roger on 2016/6/16.
 */
public interface EventSignService {

    public EventSign save(EventSign eventSign);

    public EventSign findByEventIdAndEventSignCreator(Integer eventId, APPUser appUser);

    public List<EventSign> getEventSignToVoteList(Integer eventId);

    public List<EventSign> getEventSignToVoteRankList(Integer eventId);

    public int increasePraiseCount(String eventSignPraise, Integer id);

    public EventSign findEntity(Integer id);

    public EventSign findEntityByEventIdAndCreator(Integer eventId, APPUser eventSignCreator);

    public List<EventSign> getEventSignByCreator(APPUser eventSignCreator);

    public List<EventSign> getEventSignByEventId(Integer eventId);
}
