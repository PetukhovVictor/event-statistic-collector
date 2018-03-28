package com.yandex.money.eventstatisticcollector;

import org.joda.time.DateTime;

/**
 * Event. DTO-модель события.
 */
final class EventDTO {
    /**
     * Время наступления события (количество милисекунд).
     */
    private Long date;

    /**
     * Связанные с отметкой события дополнительные данные.
     */
    private Object data;

    /**
     * Конструктор.
     *
     * @param time Время наступления события.
     * @param data Связанные с отметкой события дополнительные данные.
     */
    EventDTO(DateTime time, Object data) {
        this.date = time.getMillis();
        this.data = data;
    }

    /**
     * Конструктор.
     *
     * @param time Время наступления события.
     */
    EventDTO(DateTime time) {
        this(time, null);
    }
}
