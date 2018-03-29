package com.yandex.money.eventstatisticcollector;

import java.util.Date;

interface EventManager {
    /**
     * Учет события.
     *
     * @param time Время наступления события.
     */
    void considerEvent(Date time);

    /**
     * Подсчет колечества событий, произошедших за последнюю минуту.
     */
    Integer countEventsInLastMinute();

    /**
     * Подсчет колечества событий, произошедших за последний час.
     */
    Integer countEventsInLastHour();

    /**
     * Подсчет колечества событий, произошедших за последний день.
     */
    Integer countEventsInLastDay();
}
