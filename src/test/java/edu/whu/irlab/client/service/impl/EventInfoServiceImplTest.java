package edu.whu.irlab.client.service.impl;

import edu.whu.irlab.client.entity.EventInfo;
import edu.whu.irlab.client.service.EventInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Roger on 2016/6/23.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext.xml"})
public class EventInfoServiceImplTest {

    @Autowired
    private EventInfoService eventInfoService;

    @Test
    public void getEventsWithCondition() throws Exception {
        List<EventInfo> eipage1 = eventInfoService.getEventsWithCondition(1, 10, "", "","1", "1", "").getContent();
        List<EventInfo> eipage2 = eventInfoService.getEventsWithCondition(2, 10, "", "","1", "1", "").getContent();

        for (EventInfo ei: eipage1){
            System.out.println(ei.getEventTitle());
        }

        System.out.println("----------------------");

        for (EventInfo ei: eipage2){
            System.out.println(ei.getEventTitle());
        }
    }

}