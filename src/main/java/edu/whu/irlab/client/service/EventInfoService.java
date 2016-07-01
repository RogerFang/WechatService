package edu.whu.irlab.client.service;

import edu.whu.irlab.client.entity.APPUser;
import edu.whu.irlab.client.entity.EventInfo;
import edu.whu.irlab.client.entity.WebEventInfo;
import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Roger on 2016/6/7.
 */
public interface EventInfoService {

    public EventInfo findEntity(Integer id);

    public EventInfo saveEntity(EventInfo eventInfo);

    public void deleteEntity(Integer id);

    public List<EventInfo> findAll();

    public Page<EventInfo> getEventsList(int pageNumber, int pageSize);

    public Page<EventInfo> getEventsWithCondition(int pageNumber, int pageSize, String eventCls, String eventArea, String eventSort, String eventPay, String query);

    public Page<EventInfo> getEventsWithType(int pageNumber, int pageSize, String eventType);

    public Page<EventInfo> getEventsWithTimeAndType(int pageNumber, int pageSize, String eventType, String filterWord,
                                                    Date now, String timeOption);

    public EventInfo saveOffline(WebEventInfo webEventInfo);

    public EventInfo saveOnline(WebEventInfo webEventInfo);

    public List<EventInfo> getEventsByEventCreator(APPUser eventCreator);

    public Page<EventInfo> search(int pageNumber, int pageSize, String eventTitle);

    public int increaseCommentCount(Integer eventId);
}
