package com.yandex.money.eventstatisticcollector;

import org.joda.time.DateTime;

interface EventManager {
    /**
     * Учет события.
     *
     * @param time Время наступления события.
     */
    void considerEvent(DateTime time);

    /**
     * Подсчет колечества событий, произошедших за последнюю минуту.
     */
    Long countEventsInLastMinute();

    /**
     * Подсчет колечества событий, произошедших за последний час.
     */
    Long countEventsInLastHour();

    /**
     * Подсчет колечества событий, произошедших за последний день.
     */
    Long countEventsInLastDay();
}
