# event-statistic-collector

Библиотека для учета событий и получения количественной статистики по ним.

## Описание API

Библиотека предоставляет следующие методы API (класс `JEventManager`):
- `considerEvent` — учёт события. В качестве параметра передается дата наступления события.
- `countEventsInLastMinute` — получение количества событий за последнюю минуту.
- `countEventsInLastHour` — получение количества событий за последний час.
- `countEventsInLastDay` — получение количества событий за последний день.
- `cleanEvents` — очистка всех имеющихся событий.

## Хранение событий

Для хранения событий используется карта, ключи которой расположены на красно-черном дереве (TreeMap).
Благодаря такому расположению ключей имеется возможность достаточно быстро получить заданный срез (по заданной отметке времени).

### Пример использования

Скачать библиотеку можно из раздела [Releases](https://github.com/PetukhovVictor/event-statistic-collector/releases).

После добавления библиотеки в проект Вы можете использовать доступный функционал:
```java
package com.company;

import com.yandex.money.eventstatisticcollector.JEventManager;

import java.util.Date;

public class Test {
    public static void main(String[] args) {
        EventManager manager = JEventManager.getInstance();
        manager.considerEvent(new Date());
        manager.considerEvent(new Date());
        manager.considerEvent(new Date());

        try {
            Thread.sleep(61 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        manager.considerEvent(new Date());
        manager.considerEvent(new Date());

        System.out.println(manager.countEventsInLastMinute());
        System.out.println(manager.countEventsInLastHour());
    }
}
```

Вывод для приведенного примера:
```
2
5

Process finished with exit code 0
```
