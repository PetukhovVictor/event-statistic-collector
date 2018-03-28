package com.yandex.money.eventstatisticcollector;

import java.util.Date;

public class Test {
    public static void main(String[] args) {
        EventManager manager = new EventManager();
        manager.write(new Date());

        manager.countEvents(new Date());
    }
}