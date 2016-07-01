package edu.whu.irlab.client.comparator;

import edu.whu.irlab.client.entity.EventSign;

import java.util.Comparator;

/**
 * Created by Roger on 2016/6/20.
 */
public class EventSignComparator implements Comparator<EventSign> {
    @Override
    public int compare(EventSign o1, EventSign o2) {
        return o1.getEventSignId().compareTo(o2.getEventSignId());
    }
}
