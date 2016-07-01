package edu.whu.irlab.client.service.impl;

import com.google.common.collect.Maps;
import edu.whu.irlab.client.entity.APPUser;
import edu.whu.irlab.client.entity.EventSign;
import edu.whu.irlab.client.entity.EventVote;
import edu.whu.irlab.client.repository.EventVoteDao;
import edu.whu.irlab.client.service.EventVoteService;
import edu.whu.irlab.client.util.CDynamicSpecifications;
import edu.whu.irlab.client.util.CSearchFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Roger on 2016/6/17.
 */
@Service
public class EventVoteServiceImpl implements EventVoteService {

    @Autowired
    private EventVoteDao eventVoteDao;

    @Override
    public EventVote save(EventVote eventVote) {
        return eventVoteDao.save(eventVote);
    }

    @Override
    public boolean checkVote(Integer eventId, APPUser voteCreator) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        // 当天零点时间
        Date curDate = cal.getTime();
        // System.out.println(curDate);
        Map<String, CSearchFilter> filters = Maps.newHashMap();
        filters.put("voteEventId", new CSearchFilter("voteEventId", CSearchFilter.COperator.EQ, eventId));
        filters.put("voteCreator", new CSearchFilter("voteCreator", CSearchFilter.COperator.EQ, voteCreator));
        filters.put("voteDate", new CSearchFilter("voteDate", CSearchFilter.COperator.GTE, curDate));
        Specification<EventVote> spec = CDynamicSpecifications.bySearchFilter(filters.values(), EventVote.class);
        if (eventVoteDao.findAll(spec).size() > 0){
            return false;
        }else {
            return true;
        }
    }

    @Override
    public List<EventVote> getEventVoteListByVoteCreator(APPUser voteCreator) {
        Sort sort = new Sort(Sort.Direction.DESC, "voteDate");
        Map<String, CSearchFilter> filters = Maps.newHashMap();
        filters.put("voteCreator", new CSearchFilter("voteCreator", CSearchFilter.COperator.EQ, voteCreator));
        Specification<EventVote> spec = CDynamicSpecifications.bySearchFilter(filters.values(), EventVote.class);
        return eventVoteDao.findAll(spec, sort);
    }
}
