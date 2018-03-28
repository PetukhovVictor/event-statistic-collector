# event-statistic-collector

Библиотека для учета событий и получения количественной статистики по ним.

## Описание API

Библиотека предоставляет следующие методы API (класс `EsEventManager`):
- `considerEvent` — учёт события. В качестве параметра передается дата наступления события.
- `countEventsInLastMinute` — получение количества событий за последнюю минуту.
- `countEventsInLastHour` — получение количества событий за последний час.
- `countEventsInLastDay` — получение количества событий за последний день.

Также имеется два дополнительных методы для установки используемого для хранения записей в Elasticsearch индекса и типа:
- `setEsIndex` — установка используемого индекса для хранения записей.
- `setEsType` — установка используемого типа записей.

## Хранение событий

Для хранения событий используется [Elasticsearch](https://www.elastic.co/products/elasticsearch).

### Подключение к Elasticsearch
По умолчанию установка соединения происходит со следующими параметрами:
- имя хоста: `localhost`;
- номер порта: `9300`;
- название кластера: `production`;
- название узла (node): `main`.

Вы можете установить данные параметры самостоятельно, передав их в конструктор класса `EsEventManager`:
- `new EsEventManager('my_host', my_port)`;
- `new EsEventManager('my_host', my_port, 'my_cluster', 'my_node')`.

Пример конфигурации Elasticsearch приведен в [данном файле](https://github.com/PetukhovVictor/event-statistic-collector/blob/master/elasticsearch.example.yml).

### Создание индекса и отображения

Для создания необходимого индекса и отображения (mapping) в Elasticsearch Вы можете запустить [прилагаемый скрипт](https://github.com/PetukhovVictor/event-statistic-collector/blob/master/create_es_index.sh).

При необходимости внесите в скрипт правки по имени хоста, порту, имени индекса и типу записей.

### Пример использования

Скачать библиотеку можно из раздела [Assets](https://github.com/PetukhovVictor/event-statistic-collector/releases).

После добавления библиотеки в проект Вы можете использовать доступный функционал:
```java
package com.company;

import com.yandex.money.eventstatisticcollector.EsEventManager;
import org.joda.time.DateTime;

public class Main {
    public static void main(String[] args) {
        EsEventManager eventManager = new EsEventManager();

        eventManager.considerEvent(new DateTime());
        eventManager.considerEvent(new DateTime());
        eventManager.considerEvent(new DateTime());

        System.out.println(eventManager.countEventsInLastMinute());
        System.out.println(eventManager.countEventsInLastHour());
    }
}
```

Вывод для приведенного примера:
```
3
3

Process finished with exit code 0
```
