package com.yandex.money.eventstatisticcollector;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

public class JEventManagerTest {
    private static final int MILLIS_IN_MINUTE = 60 * 1000;

    private static final int MILLIS_IN_HOUR = 60 * MILLIS_IN_MINUTE;

    private static final int MILLIS_IN_DAY = 24 * MILLIS_IN_HOUR;

    @Before
    public void initEach(){
        JEventManager.getInstance().clearEvents();
    }

    @Test
    public void countEventsInLastMinute() {
        Integer expectedEventNumber = 2;

        JEventManager eventManager = JEventManager.getInstance();

        eventManager.considerEvent(new Date(System.currentTimeMillis() - MILLIS_IN_MINUTE + 100));
        eventManager.considerEvent(new Date(System.currentTimeMillis() - MILLIS_IN_MINUTE - 100));
        eventManager.considerEvent(new Date(System.currentTimeMillis() - MILLIS_IN_MINUTE + 100));
        eventManager.considerEvent(new Date(System.currentTimeMillis() - MILLIS_IN_HOUR - 100));
        eventManager.considerEvent(new Date(System.currentTimeMillis() - MILLIS_IN_HOUR));
        eventManager.considerEvent(new Date(System.currentTimeMillis() - MILLIS_IN_HOUR + 100));
        eventManager.considerEvent(new Date(System.currentTimeMillis() - MILLIS_IN_DAY - 100));
        eventManager.considerEvent(new Date(System.currentTimeMillis() - MILLIS_IN_DAY));
        eventManager.considerEvent(new Date(System.currentTimeMillis() - MILLIS_IN_DAY + 100));

        Integer eventNumber = eventManager.countEventsInLastMinute();

        Assert.assertEquals(expectedEventNumber, eventNumber);
    }

    @Test
    public void countEventsInLastHour() {
        Integer expectedEventNumber = 4;

        JEventManager eventManager = JEventManager.getInstance();

        eventManager.considerEvent(new Date(System.currentTimeMillis() - MILLIS_IN_HOUR - 100));
        eventManager.considerEvent(new Date(System.currentTimeMillis() - MILLIS_IN_HOUR - 100));
        eventManager.considerEvent(new Date(System.currentTimeMillis() - MILLIS_IN_HOUR + 100));
        eventManager.considerEvent(new Date(System.currentTimeMillis() - MILLIS_IN_MINUTE - 100));
        eventManager.considerEvent(new Date(System.currentTimeMillis() - MILLIS_IN_MINUTE));
        eventManager.considerEvent(new Date(System.currentTimeMillis() - MILLIS_IN_MINUTE + 100));
        eventManager.considerEvent(new Date(System.currentTimeMillis() - MILLIS_IN_DAY - 100));
        eventManager.considerEvent(new Date(System.currentTimeMillis() - MILLIS_IN_DAY));
        eventManager.considerEvent(new Date(System.currentTimeMillis() - MILLIS_IN_DAY + 100));

        Integer eventNumber = eventManager.countEventsInLastHour();

        Assert.assertEquals(expectedEventNumber, eventNumber);
    }

    @Test
    public void countEventsInLastDay() {
        Integer expectedEventNumber = 7;

        JEventManager eventManager = JEventManager.getInstance();

        eventManager.considerEvent(new Date(System.currentTimeMillis() - MILLIS_IN_DAY - 100));
        eventManager.considerEvent(new Date(System.currentTimeMillis() - MILLIS_IN_DAY - 100));
        eventManager.considerEvent(new Date(System.currentTimeMillis() - MILLIS_IN_DAY + 100));
        eventManager.considerEvent(new Date(System.currentTimeMillis() - MILLIS_IN_HOUR - 100));
        eventManager.considerEvent(new Date(System.currentTimeMillis() - MILLIS_IN_HOUR));
        eventManager.considerEvent(new Date(System.currentTimeMillis() - MILLIS_IN_HOUR + 100));
        eventManager.considerEvent(new Date(System.currentTimeMillis() - MILLIS_IN_MINUTE - 100));
        eventManager.considerEvent(new Date(System.currentTimeMillis() - MILLIS_IN_MINUTE));
        eventManager.considerEvent(new Date(System.currentTimeMillis() - MILLIS_IN_MINUTE + 100));

        Integer eventNumber = eventManager.countEventsInLastDay();

        Assert.assertEquals(expectedEventNumber, eventNumber);
    }
}
