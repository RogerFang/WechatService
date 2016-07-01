package edu.whu.irlab.client.service.impl;

import com.google.common.collect.Maps;
import edu.whu.irlab.client.entity.APPUser;
import edu.whu.irlab.client.entity.EventInfo;
import edu.whu.irlab.client.entity.WebEventInfo;
import edu.whu.irlab.client.repository.EventInfoDao;
import edu.whu.irlab.client.service.EventInfoService;
import edu.whu.irlab.client.util.CDynamicSpecifications;
import edu.whu.irlab.client.util.CSearchFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springside.modules.persistence.DynamicSpecifications;
import org.springside.modules.persistence.SearchFilter;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Roger on 2016/6/7.
 */
@Service
public class EventInfoServiceImpl implements EventInfoService{

    @Autowired
    private EventInfoDao eiDao;

    @Override
    public EventInfo findEntity(Integer id) {
        return eiDao.findOne(id);
    }

    @Override
    public EventInfo saveEntity(EventInfo eventInfo) {
        return eiDao.save(eventInfo);
    }

    @Override
    public void deleteEntity(Integer id) {
        eiDao.delete(id);
    }

    @Override
    public List<EventInfo> findAll() {
        return (List<EventInfo>) eiDao.findAll();
    }

    @Override
    public Page<EventInfo> getEventsList(int pageNumber, int pageSize) {
        Sort sort = new Sort(Sort.Direction.DESC, "eventCreatetime");
        Map<String, CSearchFilter> filters = Maps.newHashMap();
        filters.put("eventProcess", new CSearchFilter("eventProcess", CSearchFilter.COperator.EQ, 1));
        filters.put("eventStatus", new CSearchFilter("eventStatus", CSearchFilter.COperator.EQ, 1));
        // eventEndtime大于本机现在时间
        filters.put("eventEndtime", new CSearchFilter("eventEndtime", CSearchFilter.COperator.GTE, new Date()));
        Specification<EventInfo> spec = CDynamicSpecifications.bySearchFilter(filters.values(), EventInfo.class);
        PageRequest pageRequest = new PageRequest(pageNumber - 1, pageSize, sort);
        return eiDao.findAll(spec, pageRequest);
    }

    @Override
    public Page<EventInfo> getEventsWithCondition(int pageNumber, int pageSize,
                                                  String eventCls, String eventArea, String eventSort, String eventPay, String query) {
        Sort sort = null;
        if (eventSort.equals("0")){
            sort = new Sort(Sort.Direction.DESC, "eventMemberCount");
        }else {
            sort = new Sort(Sort.Direction.DESC, "eventCreatetime");
        }

        Map<String, CSearchFilter> filters = Maps.newHashMap();
        filters.put("eventProcess", new CSearchFilter("eventProcess", CSearchFilter.COperator.EQ, 1));
        filters.put("eventStatus", new CSearchFilter("eventStatus", CSearchFilter.COperator.EQ, 1));
        if (!eventCls.equals("")){
            filters.put("eventCls", new CSearchFilter("eventCls", CSearchFilter.COperator.EQ, eventCls));
        }
        if (eventPay.equals("0")){
            filters.put("text2", new CSearchFilter("text2", CSearchFilter.COperator.EQ, "0"));
        }
        if (eventPay.equals("1")){
            filters.put("text2", new CSearchFilter("text2", CSearchFilter.COperator.NEQ, "0"));
        }
        if (!query.equals("")){
            filters.put("eventTitle", new CSearchFilter("eventTitle", CSearchFilter.COperator.LIKE, query));
        }
        // eventEndtime大于本机现在时间
        filters.put("eventEndtime", new CSearchFilter("eventEndtime", CSearchFilter.COperator.GTE, new Date()));
        Specification<EventInfo> spec = CDynamicSpecifications.bySearchFilter(filters.values(), EventInfo.class);
        PageRequest pageRequest = new PageRequest(pageNumber - 1, pageSize, sort);
        return eiDao.findAll(spec, pageRequest);
    }

    @Override
    public Page<EventInfo> getEventsWithType(int pageNumber, int pageSize, String eventType) {
        Sort sort = new Sort(Sort.Direction.DESC, "eventBegintime");
        Map<String, SearchFilter> filters = Maps.newHashMap();
        filters.put("eventType", new SearchFilter("eventType", SearchFilter.Operator.EQ, eventType));
        // filters.put("eventBegintime", new SearchFilter("eventBegintime",
        // Operator.GTE, new Date()));
        Specification<EventInfo> spec = DynamicSpecifications.bySearchFilter(filters.values(), EventInfo.class);
        PageRequest pageRequest = new PageRequest(pageNumber - 1, pageSize, sort);
        return eiDao.findAll(spec, pageRequest);
    }

    @Override
    public Page<EventInfo> getEventsWithTimeAndType(int pageNumber, int pageSize, String eventType, String filterWord, Date now, String timeOption) {
        Sort sort = new Sort(Sort.Direction.DESC, "eventCreatetime");
        Map<String, SearchFilter> filters = Maps.newHashMap();
        if (eventType != null && eventType.length() > 0) {
            filters.put("eventType", new SearchFilter("eventType", SearchFilter.Operator.EQ, eventType));
        }
        System.out.println("------" + filterWord);
        if (filterWord != null && filterWord.length() > 0) {
            System.out.println("添加检索条件");
            filters.put("eventTitle", new SearchFilter("eventTitle", SearchFilter.Operator.LIKE, filterWord));
        }
        // System.out.println(now.toGMTString());
        if (timeOption != null && timeOption.equals("current")) {
            filters.put("eventBegintime", new SearchFilter("eventBegintime", SearchFilter.Operator.LT, now));
            filters.put("eventEndtime", new SearchFilter("eventEndtime", SearchFilter.Operator.GTE, now));
        } else {
            filters.put("eventBegintime", new SearchFilter("eventBegintime", SearchFilter.Operator.GTE, now));
        }
        Specification<EventInfo> spec = DynamicSpecifications.bySearchFilter(filters.values(), EventInfo.class);
        PageRequest pageRequest = new PageRequest(pageNumber - 1, pageSize, sort);
        return eiDao.findAll(spec, pageRequest);
    }

    @Override
    public EventInfo saveOffline(WebEventInfo webEventInfo) {
        EventInfo eventInfo = new EventInfo();
        eventInfo.setEventCls(webEventInfo.getEventCls());
        eventInfo.setEventTitle(webEventInfo.getEventTitle());
        eventInfo.setEventBegintime(webEventInfo.getEventBegintime());
        eventInfo.setEventEndtime(webEventInfo.getEventEndtime());
        eventInfo.setLimitNum(webEventInfo.getLimitNum());
        eventInfo.setEventPlace(webEventInfo.getEventPlace());
        eventInfo.setEventContent(webEventInfo.getEventContent());

        eventInfo.setEventType("1");
        eventInfo.setEventCreator(webEventInfo.getEventCreator());
        eventInfo.setEventCreatetime(new Date());
        eventInfo.setEventStatus("1");
        // 待审核
        eventInfo.setEventProcess("0");
        eventInfo.setEventCommentCount("0");
        eventInfo.setEventMemberCount("0");
        eventInfo.setEventPic(webEventInfo.getEventPic());
        eventInfo.setText2("0");
        return eiDao.save(eventInfo);
    }

    @Override
    public EventInfo saveOnline(WebEventInfo webEventInfo) {
        EventInfo eventInfo = new EventInfo();
        eventInfo.setEventCls(webEventInfo.getEventCls());
        eventInfo.setEventTitle(webEventInfo.getEventTitle());
        eventInfo.setEventBegintime(webEventInfo.getEventBegintime());
        eventInfo.setEventEndtime(webEventInfo.getEventEndtime());
        eventInfo.setLimitNum(webEventInfo.getLimitNum());
        eventInfo.setEventContent(webEventInfo.getEventContent());

        if (webEventInfo.getVoteTag() == 1){
            eventInfo.setEventType("3");
        }else {
            eventInfo.setEventType("2");
        }
        eventInfo.setEventCreator(webEventInfo.getEventCreator());
        eventInfo.setEventCreatetime(new Date());
        eventInfo.setEventStatus("1");
        // 待审核
        eventInfo.setEventProcess("0");
        eventInfo.setEventCommentCount("0");
        eventInfo.setEventMemberCount("0");
        eventInfo.setEventPic(webEventInfo.getEventPic());
        eventInfo.setText2("0");
        return eiDao.save(eventInfo);
    }

    @Override
    public List<EventInfo> getEventsByEventCreator(APPUser eventCreator) {
        Sort sort = new Sort(Sort.Direction.DESC, "eventCreatetime");
        Map<String, CSearchFilter> filters = Maps.newHashMap();
        filters.put("eventCreator", new CSearchFilter("eventCreator", CSearchFilter.COperator.EQ, eventCreator));
        Specification<EventInfo> spec = CDynamicSpecifications.bySearchFilter(filters.values(), EventInfo.class);
        return eiDao.findAll(spec, sort);
    }

    @Override
    public Page<EventInfo> search(int pageNumber, int pageSize, String eventTitle) {
        Sort sort = new Sort(Sort.Direction.DESC, "eventCreatetime");
        Map<String, CSearchFilter> filters = Maps.newHashMap();
        filters.put("eventProcess", new CSearchFilter("eventProcess", CSearchFilter.COperator.EQ, 1));
        filters.put("eventStatus", new CSearchFilter("eventStatus", CSearchFilter.COperator.EQ, 1));
        // eventEndtime大于本机现在时间
        filters.put("eventEndtime", new CSearchFilter("eventEndtime", CSearchFilter.COperator.GTE, new Date()));
        filters.put("eventTitle", new CSearchFilter("eventTitle", CSearchFilter.COperator.LIKE, eventTitle));
        Specification<EventInfo> spec = CDynamicSpecifications.bySearchFilter(filters.values(), EventInfo.class);
        PageRequest pageRequest = new PageRequest(pageNumber - 1, pageSize, sort);
        return eiDao.findAll(spec, pageRequest);
    }

    @Override
    public int increaseCommentCount(Integer eventId) {
        EventInfo eventInfo = findEntity(eventId);
        int commentCount = Integer.parseInt(eventInfo.getEventCommentCount()) + 1;
        return eiDao.increaseCommentCount(String.valueOf(commentCount), eventId);
    }
}
