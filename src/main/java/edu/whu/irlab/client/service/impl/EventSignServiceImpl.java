package edu.whu.irlab.client.service.impl;

import com.google.common.collect.Maps;
import edu.whu.irlab.client.entity.APPUser;
import edu.whu.irlab.client.entity.EventSign;
import edu.whu.irlab.client.repository.EventSignDao;
import edu.whu.irlab.client.service.EventSignService;
import edu.whu.irlab.client.util.CDynamicSpecifications;
import edu.whu.irlab.client.util.CSearchFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Roger on 2016/6/17.
 */
@Service
public class EventSignServiceImpl implements EventSignService {

    @Autowired
    private EventSignDao eventSignDao;

    @Override
    public EventSign save(EventSign eventSign) {
        return eventSignDao.save(eventSign);
    }

    @Override
    public EventSign findByEventIdAndEventSignCreator(Integer eventId, APPUser appUser) {
        return eventSignDao.findByEventIdAndEventSignCreator(eventId, appUser);
    }

    @Override
    public List<EventSign> getEventSignToVoteList(Integer eventId) {
        Sort sort = new Sort(Sort.Direction.ASC, "eventSignCreatetime");
        Map<String, CSearchFilter> filters = Maps.newHashMap();
        filters.put("eventId", new CSearchFilter("eventId", CSearchFilter.COperator.EQ, eventId));
        filters.put("eventSignProcess", new CSearchFilter("eventSignProcess", CSearchFilter.COperator.EQ, 1));
        filters.put("eventSignStatus", new CSearchFilter("eventSignStatus", CSearchFilter.COperator.EQ, 1));
        Specification<EventSign> spec = CDynamicSpecifications.bySearchFilter(filters.values(), EventSign.class);
        // PageRequest pageRequest = new PageRequest(pageNumber - 1, pageSize, sort);
        return eventSignDao.findAll(spec, sort);
    }

    @Override
    public List<EventSign> getEventSignToVoteRankList(Integer eventId) {
        Sort sort = new Sort(Sort.Direction.DESC, "eventSignPraise");
        Map<String, CSearchFilter> filters = Maps.newHashMap();
        filters.put("eventId", new CSearchFilter("eventId", CSearchFilter.COperator.EQ, eventId));
        filters.put("eventSignProcess", new CSearchFilter("eventSignProcess", CSearchFilter.COperator.EQ, 1));
        filters.put("eventSignStatus", new CSearchFilter("eventSignStatus", CSearchFilter.COperator.EQ, 1));
        Specification<EventSign> spec = CDynamicSpecifications.bySearchFilter(filters.values(), EventSign.class);
        // PageRequest pageRequest = new PageRequest(pageNumber - 1, pageSize, sort);
        return eventSignDao.findAll(spec, sort);
    }

    @Override
    public int increasePraiseCount(String eventSignPraise, Integer id) {
        return eventSignDao.increasePraiseCount(eventSignPraise, id);
    }

    @Override
    public EventSign findEntity(Integer id) {
        return eventSignDao.findOne(id);
    }

    @Override
    public EventSign findEntityByEventIdAndCreator(Integer eventId, APPUser eventSignCreator) {
        return eventSignDao.findByEventIdAndEventSignCreator(eventId, eventSignCreator);
    }

    @Override
    public List<EventSign> getEventSignByCreator(APPUser eventSignCreator) {
        Sort sort = new Sort(Sort.Direction.DESC, "eventSignCreatetime");
        Map<String, CSearchFilter> filters = Maps.newHashMap();
        filters.put("eventSignCreator", new CSearchFilter("eventSignCreator", CSearchFilter.COperator.EQ, eventSignCreator));
        Specification<EventSign> spec = CDynamicSpecifications.bySearchFilter(filters.values(), EventSign.class);
        return eventSignDao.findAll(spec, sort);
    }

    @Override
    public List<EventSign> getEventSignByEventId(Integer eventId) {
        // Sort sort = new Sort(Sort.Direction.DESC, "eventSignProcess", "eventSignCreatetime");
        Sort sort = new Sort(new Sort.Order(Sort.Direction.ASC, "eventSignProcess"), new Sort.Order(Sort.Direction.DESC, "eventSignCreatetime"));
        Map<String, CSearchFilter> filters = Maps.newHashMap();
        filters.put("eventId", new CSearchFilter("eventId", CSearchFilter.COperator.EQ, eventId));
        Specification<EventSign> spec = CDynamicSpecifications.bySearchFilter(filters.values(), EventSign.class);
        return eventSignDao.findAll(spec, sort);
    }
}
