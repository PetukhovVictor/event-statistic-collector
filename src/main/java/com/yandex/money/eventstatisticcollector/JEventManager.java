package com.yandex.money.eventstatisticcollector;

import java.util.Date;
import java.util.TreeMap;

/**
 * JEventManager.
 * Класс, предоставляющий функционал для учета и подсчета событий.
 * Класс является сингтоном, т. к. хранилище событий должно быть отвязано от конкретного инстанса.
 */
public final class JEventManager implements EventManager {
    /**
     * Инстанс JEventManager.
     */
    private static JEventManager ourInstance = new JEventManager();

    /**
     * Получение единственного (сингтон) инстанса JEventManager.
     */
    public static JEventManager getInstance() {
        return ourInstance;
    }

    /**
     * Конструктор.
     */
    private JEventManager() {

    }

    /**
     * Древовидная карта событий.
     * Используется красно-черное дерево для размещения времен наступления событий (для быстрого выбора среза по заданному промежутку).
     * Значение является счетчиком событий в заданную ключом миллисекунду (отметку времени).
     */
    private TreeMap<Long, Integer> events = new TreeMap<>();

    /**
     * Количество миллисекунд в минуте.
     */
    private static final int MILLIS_IN_MINUTE = 60 * 1000;

    /**
     * Количество миллисекунд в часе.
     */
    private static final int MILLIS_IN_HOUR = 60 * MILLIS_IN_MINUTE;

    /**
     * Количество миллисекунд в дне.
     */
    private static final int MILLIS_IN_DAY = 24 * MILLIS_IN_HOUR;

    /**
     * Учет события.
     * Если в данную миллисекунду событие уже было учтено, инкрементируется соответствующий счетчик.
     *
     * @param date Время наступления события.
     */
    public void considerEvent(Date date) {
        Long millis = date.getTime();

        if (events.containsKey(millis)) {
            events.replace(millis, events.get(millis) + 1);
        } else {
            events.put(millis, 1);
        }
    }

    /**
     * Подсчет колечества событий, произошедших с заданного времени.
     * Выбирается соответствующий срез по дереву, по которому суммируются значения элементов (счетчики событий по отметкам времени).
     *
     * @param date Время, начиная с которого нужно подсчитать количество событий.
     */
    private Integer countEvents(Date date) {
        Long millis = date.getTime();

        return events.tailMap(millis).values().stream().mapToInt(Integer::intValue).sum();
    }

    /**
     * Подсчет колечества событий, произошедших за последнюю минуту.
     */
    public Integer countEventsInLastMinute() {
        Date requiredTime = new Date(System.currentTimeMillis() - MILLIS_IN_MINUTE);

        return countEvents(requiredTime);
    }

    /**
     * Подсчет колечества событий, произошедших за последний час.
     */
    public Integer countEventsInLastHour() {
        Date requiredTime = new Date(System.currentTimeMillis() - MILLIS_IN_HOUR);

        return countEvents(requiredTime);
    }

    /**
     * Подсчет колечества событий, произошедших за последний день.
     */
    public Integer countEventsInLastDay() {
        Date requiredTime = new Date(System.currentTimeMillis() - MILLIS_IN_DAY);

        return countEvents(requiredTime);
    }
}
