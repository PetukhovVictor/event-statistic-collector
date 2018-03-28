package com.yandex.money.eventstatisticcollector;

import java.util.Date;

class Event {
    private Long date;
    private Object data;

    Event(Date date, Object data) {
        this.date = date.getTime();
        this.data = data;
    }

    Event(Date date) {
        this(date, null);
    }
}
